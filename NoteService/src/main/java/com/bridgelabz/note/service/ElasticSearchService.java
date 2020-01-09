/******************************************************************************
 *  Purpose: This class having methods related to ElasticSearch engine
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   02-12-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.note.entity.Note;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSearchService {

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Purpose: this method is used to add document to ElasticSearch engine
	 * 
	 * @param document parameter which contains the entity which is going to store
	 * @return returns the stored entity with proper response message
	 * @throws IOException if communicating with ElasticSearch if some failure occur
	 *                     then that exception can handles by this IOException
	 */
	public Response addNote(Note document) throws IOException {
		@SuppressWarnings("unchecked")
		Map<Integer, Object> documentMapper = objectMapper.convertValue(document, Map.class);
		IndexRequest indexRequest = new IndexRequest(Constant.INDEX, Constant.TYPE,
				String.valueOf(document.getNoteId())).source(documentMapper);
		IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

		return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE, indexResponse.getResult().name());

	}

	/**
	 * Purpose: this method is used to find document from ES(ElasticSearch) by using
	 * noteId
	 * 
	 * @param id this will specify which document to search by using document note
	 *           id
	 * @return returns the stored entity with proper response message
	 * @throws IOException if communicating with ElasticSearch if some failure occur
	 *                     then that exception can handles by this IOException
	 */
	public Response findById(int id) throws IOException {
		GetRequest getRequest = new GetRequest(Constant.INDEX, Constant.TYPE, String.valueOf(id));

		GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
		Map<String, Object> resultMap = getResponse.getSource();

		return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE,
				objectMapper.convertValue(resultMap, Note.class));

	}

	/**
	 * Purpose: this method is used to update document in the ES(ElasticSearch)
	 * engine
	 * 
	 * @param document parameter which contains the entity which is going to update
	 * @return returns the stored entity with proper response message
	 * @throws IOException if communicating with ElasticSearch if some failure occur
	 *                     then that exception can handles by this IOException
	 */
	public Response updateNote(Note document) throws IOException {
		Note resultDocument = (Note) findById(document.getNoteId()).getData();

		UpdateRequest updateRequest = new UpdateRequest(Constant.INDEX, Constant.TYPE,
				String.valueOf(resultDocument.getNoteId()));

		@SuppressWarnings("unchecked")
		Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);

		updateRequest.doc(documentMapper);

		UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

		return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE, updateResponse.getResult().name());

	}

	/**
	 * Purpose: this method will find all document which is out there in
	 * ElasticSearch engine
	 * 
	 * @return returns all the stored document with proper response message
	 * @throws IOException if communicating with ElasticSearch if some failure occur
	 *                     then that exception can handles by this IOException
	 */
	public Response findAll() throws IOException {
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE, getSearchResult(searchResponse));
	}

	/**
	 * Purpose: this method will get search result from the ElasticSearch engine and
	 * return list of documents which is there in ElasticSearch
	 * 
	 * @param response this will getting response from different methods
	 * @return response returns all the stored document with list of records
	 */
	private List<Note> getSearchResult(SearchResponse response) {
		SearchHit[] searchHit = response.getHits().getHits();
		List<Note> noteDocuments = new ArrayList<Note>();
		if (searchHit.length > 0) {
			Arrays.stream(searchHit)
					.forEach(hit -> noteDocuments.add(objectMapper.convertValue(hit.getSourceAsMap(), Note.class)));
		}
		return noteDocuments;
	}

	/**
	 * Purpose: this method is used to search document from ElasticSearch(ES) engine
	 * on the basis of Note entity title or description
	 * 
	 * @param key this will specify what to search from ES engine
	 * @return returns the searched entity with proper response message
	 * @throws IOException if communicating with ElasticSearch if some failure occur
	 *                     then that exception can handles by this IOException
	 */
	public Response searchByNoteTitle(String key) throws IOException {

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices(Constant.INDEX);
		searchRequest.types(Constant.TYPE);

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.query(QueryBuilders.boolQuery()
				.should(QueryBuilders.queryStringQuery("*" + key + "*").lenient(true).field("title").field("description")));
		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

		return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE, getSearchResult(searchResponse));
	}

	/**
	 * Purpose: this method is used to delete the document from the ElasticSearch
	 * engine on the basis of document id
	 * 
	 * @param id this will specify which document to delete
	 * @return returns proper response message and left remaining document in the
	 *         response
	 * @throws IOException if communicating with ElasticSearch if some failure occur
	 *                     then that exception can handles by this IOException
	 */
	public Response deleteNoteDocument(String id) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest(Constant.INDEX, Constant.TYPE, id);
		DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

		return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE, response.getResult().name());

	}

}
