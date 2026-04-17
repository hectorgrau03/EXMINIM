package edu.upc.dsa;
import edu.upc.dsa.models.Institute;
import edu.upc.dsa.models.Operation;

import java.util.List;

public interface MathManager {

    void addOperation(String expression, String studentId, String instituteId);
    Operation processNextOperation();
    List<Operation> getOperationsByInstitute(String instituteId);
    List<Operation> getOperationsByStudent(String studentId);
    int size();
    void clear();
    List<Institute> getInstitutesByOperationsDesc();
}
