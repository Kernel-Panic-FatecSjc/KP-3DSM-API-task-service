package com.kernelpanic.task_service.dtos;

import java.util.Map;

public class ResumoProjetoDTO {
    private Integer projectId;
    private Map<String, Long> status;

    public ResumoProjetoDTO(Integer projectId, Map<String, Long> status){
        this.projectId = projectId;
        this.status= status;
    }

    public Integer getPorojectId(){
        return projectId;
    }

    public Map<String, Long>getStatus(){
        return status;
    }

}
