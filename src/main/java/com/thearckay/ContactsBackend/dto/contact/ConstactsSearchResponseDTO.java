package com.thearckay.ContactsBackend.dto.contact;

import java.util.List;

public class ConstactsSearchResponseDTO {
    private String query;
    private Integer total;
    private List<ContactResponseDTO> results;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ContactResponseDTO> getResults() {
        return results;
    }

    public void setResults(List<ContactResponseDTO> results) {
        this.results = results;
    }
}
