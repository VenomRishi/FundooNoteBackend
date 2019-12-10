package com.bridgelabz.note;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.note.service.INoteService;


@SpringBootTest
public class NoteServiceTest {
	
	private MockMvc mockMvc;
	@Spy
	@InjectMocks
	private INoteService noteService;
	@Mock
	private Environment environment;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.standaloneSetup(noteService).build();
	}
	
	@Test
	public void testCreateNote() {
		
	}

}
