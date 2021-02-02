package com.newbie.ajou.domain.typecount;

import com.newbie.ajou.domain.college.College;
import com.newbie.ajou.domain.college.CollegeRepositoryCustom;
import com.newbie.ajou.domain.type.Type;
import com.newbie.ajou.domain.type.TypeRepositoryCustom;
import com.newbie.ajou.domain.typecount.exception.TypeCountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TypeCountRepositoryCustom {
	private final TypeCountRepository typeCountRepository;
	private final TypeRepositoryCustom typeRepositoryCustom;
	private final CollegeRepositoryCustom collegeRepositoryCustom;

	public TypeCount findByTypeAndCollege(Type type, College college) {
		return typeCountRepository.findByTypeAndCollege(type, college)
				.orElseThrow(TypeCountNotFoundException::new);
	}

	public TypeCount save(TypeCount typeCount) {
		return typeCountRepository.save(typeCount);
	}

	@PostConstruct
	public void init() {
		List<Type> types = typeRepositoryCustom.findAll();
		List<College> colleges = collegeRepositoryCustom.findAll();
		for (Type type : types) {
			for (College college : colleges) {
				save(new TypeCount(type, college));
			}
		}
	}
}
