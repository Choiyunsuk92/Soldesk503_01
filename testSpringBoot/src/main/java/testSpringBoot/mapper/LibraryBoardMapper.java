package testSpringBoot.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.LibraryBoardDTO;
@Component
@Repository
public interface LibraryBoardMapper {
	public void libraryInsert(LibraryBoardDTO libraryBoardDTO) throws Exception;
}
