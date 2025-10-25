package dto;

import model.Match;

import java.util.List;

public class PaginationResponseDTO {

    private List<Match> matches;

    private Long totalPage;

    private Long currentPage;


    public PaginationResponseDTO(List<Match> matches, Long totalPage, Long firstPage) {
        this.matches = matches;
        this.totalPage = totalPage;
        this.currentPage = firstPage;
    }


    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage= totalPage;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }
}
