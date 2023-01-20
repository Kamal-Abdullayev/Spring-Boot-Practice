package com.ltp.gradesubmission;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {



	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	// @GetMapping("/")
    // public String gradeForm(Model model, @RequestParam(required = false) String id) {
    //     model.addAttribute("grade", gradeService.getGradeById(id));
    //     return "form";
    // }

	@Test
	public void testShowGradeForm() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=1");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("grade"));
	}


	// @PostMapping("/handleSubmit")
    // public String submitForm(@Valid Grade grade, BindingResult result) {
    //     if (result.hasErrors()) return "form";
    //     gradeService.submitGrade(grade);
    //     return "redirect:/grades";
    // }

	@Test
	public void testSuccessfulSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
			.param("name", "Kamal")
			.param("subject", "Test")
			.param("score", "A+");

		mockMvc.perform(request)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void testUnseccessfulSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
		.param("name", "   ")
		.param("subject", "  ")
		.param("score", "X");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"));
	}

	// @GetMapping("/grades")
    // public String getGrades(Model model) {
    //     model.addAttribute("grades", gradeService.getGrades());
    //     return "grades";
    // }

	@Test
	public void testGetGrades() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("grades"))
			.andExpect(model().attributeExists("grades"));
	}
}
