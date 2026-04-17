package edu.upc.dsa;

import edu.upc.dsa.models.Institute;
import edu.upc.dsa.models.Operation;
import org.apache.log4j.Logger;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

public class MathManagerImpl implements MathManager {

    private static Logger logger = Logger.getLogger(MathManagerImpl.class);
    private static MathManagerImpl instance;

    private final Queue<Operation> operationsQueue;
    private final List<Operation> completedOperations;
    private final Map<String, Institute> institutesMap;
    private final ReversePolishNotationImpl rpn;

    private MathManagerImpl() {
        this.operationsQueue = new LinkedList<>();
        this.completedOperations = new ArrayList<>();
        this.institutesMap = new HashMap<>();
        this.rpn = new ReversePolishNotationImpl();
    }

    public static MathManagerImpl getInstance() {
        if (instance == null) {
            instance = new MathManagerImpl();
        }
        return instance;
    }

    @Override
    public void clear() {
        this.operationsQueue.clear();
        this.completedOperations.clear();
        this.institutesMap.clear();
    }

    @Override
    public void addOperation(String expression, String studentId, String instituteId) {

        logger.info("Expresión: " + expression + ", studentId: " + studentId + ", instituteId: " + instituteId);

        String id = UUID.randomUUID().toString();
        Operation op = new Operation(id, expression, null, studentId, instituteId);
        this.operationsQueue.add(op);

        if (!this.institutesMap.containsKey(instituteId)) {
            this.institutesMap.put(instituteId, new Institute(instituteId, "Instituto " + instituteId, 0));
        }

        logger.info("Operacion encolada correctamente con ID: " + op.getId());
    }

    @Override
    public Operation processNextOperation() {
        logger.info("Procesando siguiente operación...");

        if (this.operationsQueue.isEmpty()) {
            logger.error("Cola de operaciones vacia.");
            return null;
        }

        Operation op = this.operationsQueue.poll();

        try {
            double result = this.rpn.NotacionPolaca(op.getExpression());
            op.setResult(result);
            this.completedOperations.add(op);

            Institute institute = this.institutesMap.get(op.getInstituteId());
            if (institute != null) {
                institute.setOperationsCount(institute.getOperationsCount() + 1);
            }
        } catch (RuntimeException e) {
            logger.fatal("Error procesando la operacion: " + op.getExpression(), e);
            throw e;
        }

        logger.info("Resultado calculado: " + op.getResult());
        return op;
    }

    @Override
    public List<Operation> getOperationsByInstitute(String instituteId) {
        logger.info("Id del instituto: " + instituteId);

        List<Operation> result = new ArrayList<>();
        for (Operation op : this.completedOperations) {
            if (instituteId.equals(op.getInstituteId())) {
                result.add(op);
            }
        }

        logger.info("FIN getOperationsByInstitute - Total encontradas: " + result.size());
        return result;
    }

    @Override
    public List<Operation> getOperationsByStudent(String studentId) {
        logger.info("Id del estudiante: " + studentId);

        List<Operation> result = new ArrayList<>();
        for (Operation op : this.completedOperations) {
            if (studentId.equals(op.getStudentId())) {
                result.add(op);
            }
        }

        logger.info("Total encontradas: " + result.size());
        return result;
    }

    @Override
    public List<Institute> getInstitutesByOperationsDesc() {
        logger.info("Recuperando ranking de institutos por número de operaciones...");

        List<Institute> list = new ArrayList<>(institutesMap.values());
        
        // Ordenar descendentemente por número de operaciones matemáticas realizadas
        list.sort((i1, i2) -> Integer.compare(i2.getOperationsCount(), i1.getOperationsCount()));

        logger.info("Institutos ordenados: " + list.size());
        return list;
    }

    @Override
    public int size() {
        return this.operationsQueue.size();
    }
}
