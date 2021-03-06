package testSpringBoot.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import testSpringBoot.domain.LibraryBoardDTO;
import testSpringBoot.domain.StartEndPageDTO;
@Component
@Repository
public interface LibraryBoardMapper {
	public void libraryInsert(LibraryBoardDTO libraryBoardDTO) throws Exception;
	public List<LibraryBoardDTO> getLibraryBoardList(StartEndPageDTO startEndPageDTO)throws Exception;
    public Integer getLibraryCount() throws Exception;
    public void libraryUpdate(LibraryBoardDTO dto) throws  Exception;
    public void updateReadCount(String boardNum) throws Exception;
    public void libraryDelete(String  boardNum) throws Exception;
}
