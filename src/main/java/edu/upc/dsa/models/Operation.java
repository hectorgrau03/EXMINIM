package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Operation {
    String id;
    String expression;
    Double result;
    String studentId;
    String instituteId;

    public Operation() {
        this.setId(RandomUtils.getId());
    }
    public Operation(String id, String expression, Double result, String studentId, String instituteId) {
        this.setId(id);
        this.setExpression(expression);
        this.setResult(result);
        this.setStudentId(studentId);
        this.setInstituteId(instituteId);

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getExpression() {
        return expression;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public Double getResult() {
        return result;
    }
    public void setResult(Double result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return "Operation [id="+id+", expression=" + expression + ", result=" + result +"]";
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getInstituteId() {
        return instituteId;
    }
    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

}