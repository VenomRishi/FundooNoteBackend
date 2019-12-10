//package com.bridgelabz.note;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.env.Environment;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import com.bridgelabz.note.dto.NoteDTO;
//import com.bridgelabz.note.response.Response;
//import com.bridgelabz.note.service.ImplNoteService;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class NoteServiceTest {
//
//	@SuppressWarnings("unused")
//	private MockMvc mockMvc;
//	@Spy
//	@InjectMocks
//	private ImplNoteService noteService;
//	@Mock
//	private Environment environment;
//
//	@Mock
//	private ModelMapper modelMapper;
//
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.standaloneSetup(noteService).build();
//	}
//
//	@Test
//	public void testCreateNote() {
//		String userIdToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc1OTU0NDYyfQ.ySmLSJ_BlBnXkKIv7djE8jaiistrIpKEM8WHwQnghJc";
//		NoteDTO noteDTO = new NoteDTO();
//		noteDTO.setTitle("hey");
//		noteDTO.setDescription("Hello");
////		int userId = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
//		Response response = noteService.add(userIdToken, noteDTO);
//		System.out.println(response);
//		assertEquals(200, response.getStatusCode());
//	}
//
//}
