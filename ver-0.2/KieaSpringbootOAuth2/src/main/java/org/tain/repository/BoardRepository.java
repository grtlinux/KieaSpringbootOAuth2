package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tain.domain.Board;
import org.tain.domain.User;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

	public Board findByUser(User user);
}
