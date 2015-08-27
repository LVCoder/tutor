package com.pmi.tutor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmi.tutor.dao.CategoryDAO;
import com.pmi.tutor.domain.Category;
import com.pmi.tutor.domain.Subject;
import com.pmi.tutor.dto.CategoryDTO;
import com.pmi.tutor.dto.SubjectDTO;

@Service("categoryService")
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
@Transactional
	public List<CategoryDTO> getAllCategories() {
		List<CategoryDTO> result = null;
		List<Category> categories = categoryDAO.fetchAll(Category.class);
		if (categories!=null&&!categories.isEmpty()){
			result = new ArrayList<CategoryDTO>();
			for (Category category : categories){
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setName(category.getName());
				List<SubjectDTO> subjectDTOs = new ArrayList<SubjectDTO>();
				for (Subject subject : category.getSubjects()){
					SubjectDTO subjectDTO = new SubjectDTO();
					subjectDTO.setId(subject.getId());
					subjectDTO.setName(subject.getName());
					subjectDTOs.add(subjectDTO);
				}
				Collections.sort(subjectDTOs);
				categoryDTO.setSubjects(subjectDTOs);
				result.add(categoryDTO);
			}
			Collections.sort(result);
		}
			
		return result;
	}
	
	
}
