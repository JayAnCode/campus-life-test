package com.newbie.ajou.domain.typecount;

import com.newbie.ajou.domain.college.College;
import com.newbie.ajou.domain.type.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeCountRepository extends JpaRepository<TypeCount, Integer> {
	Optional<TypeCount> findByTypeAndCollege(Type type, College college);

	List<TypeCount> findTop2ByTypeOrderByCountDesc(Type type);
}
