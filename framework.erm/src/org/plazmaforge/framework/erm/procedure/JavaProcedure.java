package org.plazmaforge.framework.erm.procedure;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.plazmaforge.framework.erm.exception.CallingException;
import org.plazmaforge.framework.util.ClassUtils;

public class JavaProcedure extends AbstractProcedure {

    private String procedureClassName;
    
    private Class<?> procedureClass;

    public String getProcedureClassName() {
        return procedureClassName;
    }

    public void setProcedureClassName(String procedureClassName) {
        this.procedureClassName = procedureClassName;
    }

    public Class<?> getProcedureClass() {
        return procedureClass;
    }

    public void setProcedureClass(Class<?> procedureClass) {
        this.procedureClass = procedureClass;
    }

    public void execute(Connection cn, Map<String, Object> parameters) throws SQLException {
	Procedure procedure = null;
	try {
	    if (getProcedureClassName() == null) {
		throw new RuntimeException("ProcedureClassName is empty");
	    }
	    Object obj = ClassUtils.newInstance(getProcedureClassName());
	    if (!(obj instanceof Procedure)) {
		throw new CallingException(getProcedureClassName() + " is not Procedure");
	    }
	    procedure = (Procedure) obj;
	    procedure.execute(cn, parameters);
	} catch (Exception ex) {
	    handleProcedureError(procedure, parameters, ex);
	}
    }
    
    protected void handleProcedureError(Procedure procedure, Map<String, Object> parameters, Exception ex) {
	throw new CallingException("Calling procedure " + getProcedureString(procedure, parameters) + "error: "  + ex.getMessage());
    }
    
    protected String getProcedureString(Procedure procedure, Map<String, Object> parameters) {
	if (procedure == null ) {
	    return "";
	}
	String name = procedure.getName();
	if (name == null) {
	    name  = procedure.getClass().getName();
	} else {
	    name = procedure.getName();
	}
	if (parameters != null) {
	    String entryPoint = getEntryPoint(parameters);
	    if (entryPoint != null) {
		name = name + ":" + entryPoint;
	    }
	}
	return "'" + name + "' ";
    }
}
