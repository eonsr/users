package com.mx.pagodiario.users.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Utileria de reflection
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Component
@Scope("prototype")
public class ReflectionUtil {
	
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ReflectionUtil.class);
	
	/**
	 * Prefijo set
	 */
	private static final String PREFIX_SET = "set";
	
	/**
	 * Prefijo get
	 */
	private static final String PREFIX_GET = "get";
	
	/**
	 * Genera un map con el nombre del metodo y el objeto Method correspondiente via
	 * Reflection.
	 * 
	 * @param object al cual se le hara la busqueda de metodos
	 * @return map con metodos del objeto de entrada.
	 */
	public Map<String, Method> getMethodsMap(Object object) {

		LOG.info("getMethodsMap");

		Map<String, Method> methodsMap = new TreeMap<String, Method>();

		if (object != null) {
			
			Method[] methods = object.getClass().getMethods();
			
			if(methods != null && methods.length > 0) {
				
				for (Method method : methods) {
					methodsMap.put(method.getName(), method);
				}
				
			} else {
				LOG.error("Sin metodos");
			}
			
			
		} else {
			LOG.error("el objeto es null");
		}

		LOG.debug("metodos en el objeto: " + methodsMap);
		
		return methodsMap;

	}
	
	
	/**
	 * Genera un map con el nombre del metodo y el objeto Method correspondiente via
	 * Reflection.
	 * 
	 * @param object al cual se le hara la busqueda de metodos
	 * @return map con metodos del objeto de entrada.
	 */
	public Map<String, Method> getMethodsWithSetMap(Object object) {

		LOG.info("getMethodsMap");

		Map<String, Method> methodsMap = new TreeMap<String, Method>();

		if (object != null) {
			
			Method[] methods = object.getClass().getMethods();
			
			if(methods != null && methods.length > 0) {
				
				for (Method method : methods) {
					
					if (method.getName().startsWith(PREFIX_SET)) {
						methodsMap.put(method.getName(), method);
					} 
					
				}
				
			} else {
				LOG.error("Sin metodos");
			}
			
			
		} else {
			LOG.error("el objeto es null");
		}

		LOG.debug("metodos en el objeto: " + methodsMap);
		
		return methodsMap;

	}
	
	/**
	 * Genera un map con el nombre del metodo y el objeto Method correspondiente via
	 * Reflection.
	 * 
	 * @param object al cual se le hara la busqueda de metodos
	 * @return map con metodos del objeto de entrada.
	 */
	public Map<String, Method> getMethodsWithGetMap(Object object) {

		LOG.info("getMethodsMap");

		Map<String, Method> methodsMap = new TreeMap<String, Method>();

		if (object != null) {
			
			Method[] methods = object.getClass().getMethods();
			
			if(methods != null && methods.length > 0) {
				
				for (Method method : methods) {
					
					if (method.getName().startsWith(PREFIX_GET)) {
						methodsMap.put(method.getName(), method);
					} 
					
				}
				
			} else {
				LOG.error("Sin metodos");
			}
			
			
		} else {
			LOG.error("el objeto es null");
		}

		LOG.debug("metodos en el objeto: " + methodsMap);
		
		return methodsMap;

	}
	
	/**
	 * Genera un map con el nombre del campo y el objeto Field correspondiente via
	 * Reflection.
	 * 
	 * @param object al cual se le hara la busqueda de campos
	 * @return map con campos del objeto de entrada.
	 */
	public Map<String, Field> getFieldsMap(Object object) {
		
		LOG.info("getFieldsMap");
		
		
		Map<String, Field> fieldsMap = new TreeMap<String, Field>();
		
		if (object != null) {
			
			Field[] fields = object.getClass().getDeclaredFields();
			
			if(fields != null && fields.length > 0) {
				
				for (Field field : fields) {
					fieldsMap.put(field.getName(), field);
				}
				
			} else {
				LOG.error("Sin campos");
			}
			
		} else {
			LOG.error("el objeto es null");
		}
		
		
		
		LOG.debug("campos en el objeto: " + fieldsMap);
		
		return fieldsMap;
		
	}
	
	/**
	 * Invoca el metodo con el parametro en el objeto de entrada via Reflection (el
	 * metodo regresa el valor del metodo como un object)
	 * 
	 * @param methodName Nombre del metodo
	 * @param param parametro del metodo
	 * @param targetObject objeto para ejecucion
	 * @return resultado de la invocacion
	 * @throws Exception error
	 */
	public Object invokeMethod(String methodName, Object[] param, Object targetObject) throws Exception {

		LOG.info("invokeMethod");

		Map<String, Method> methodsMap = new TreeMap<String, Method>();

		methodsMap = getMethodsMap(targetObject);

		Method method = methodsMap.get(methodName);

		Object object = null;

		boolean hasParams = methodHasParameters(method);

		LOG.info("hasparams: " + hasParams);

		if (hasParams) {

			LOG.debug("Se tienen parametros");

			object = invokeRespMethod(method, targetObject, param);

		} else {

			LOG.debug("No hay parametros");

			object = invokeRespMethodNoParam(method, targetObject);

		}

		LOG.debug("Resp reflection: " + object);

		return object;

	}
	
	/**
	 * Metodo para verificar el numero de parametros
	 * 
	 * @param method Metodo a inspeccionar
	 * @return true si hay parametros, false de lo contrario
	 */
	public boolean methodHasParameters(Method method) {

		LOG.info("methodHasParameters");

		boolean has = false;

		int paramNum = 0;

		if (method != null) {

			paramNum = method.getParameterCount();

			if (paramNum > 0) {
				LOG.info("El metodo contiene [" + paramNum + "] parametros");
				has = true;
			} else {
				LOG.info("El metodo no contiene parametros");
			}

		} else {
			LOG.error("El metodo es nulo");
		}

		return has;

	}
	
	/**
	 * Invoca el metodo con el parametro en el objeto de entrada via Reflection (el
	 * metodo regresa el valor del metodo como un object)
	 * 
	 * @param method Objeto Method
	 * @param obj Objeto al cual se hace referencia para invocar el metodo
	 * @param parameter parametro del metodo
	 * @return resultado de la invocacion
	 * @throws Exception error
	 */
	public Object invokeRespMethod(Method method, Object obj, Object[] parameter) throws Exception {

		LOG.info("invokeRespMethod");

		Object resp = null;

		String methodName = "";

		methodName = method.getName();

		LOG.info("methodName: " + methodName);
		LOG.info("parameter(s): " + parameter);

		resp = method.invoke(obj, parameter);

		return resp;

	}
	
	/**
	 * Invoca el metodo sin parametros en el objeto de entrada via Reflection (el
	 * metodo regresa el valor del metodo como un object)
	 * 
	 * @param method Objeto Method
	 * @param obj Objeto al cual se hace referencia para invocar el metodo
	 * @return resultado de la invocacion
	 */
	public Object invokeRespMethodNoParam(Method method, Object obj) {

		LOG.info("invokeRespMethodNoParam");

		Object resp = null;

		try {

			LOG.info("method: " + method.getName());

			resp = method.invoke(obj);

		} catch (InvocationTargetException e) {

			LOG.error(e);
			LOG.error(e.getCause());
			LOG.error(e.getTargetException());

		} catch (Exception e) {

			LOG.error(e);
			LOG.error(e);
			LOG.error(e.getCause());
		}

		return resp;

	}
	
	/**
	 * Metodo para imprimir el methodsMap
	 * 
	 * @param methodsMap map con metodos del objeto de entrada.
	 */
	public void printMethodsMap(Map<String, Method> methodsMap) {
		
		LOG.info("printMethodsMap");
		
		if (methodsMap != null && !methodsMap.isEmpty()) {
			
			methodsMap.forEach(					
					(key,value) -> {
						
						LOG.info("-------------------------------------------------");
						LOG.info("Nombre: " + key);
						LOG.info("Nombre Extendido: " + value);
						LOG.info("-------------------------------------------------");
						
					}
					
			);
			
		} else {
			LOG.error("El map es null o esta vacio");
		}
		
		
	}

	/**
	 * Metodo para imprimir el printFieldMap
	 * 
	 * @param fieldsMap map con metods del objeto de entrada.
	 */
	public void printFieldMap(Map<String, Field> fieldsMap) {
		
		LOG.info("printFieldMap");
		
		if (fieldsMap != null && !fieldsMap.isEmpty()) {
			
			fieldsMap.forEach(					
					(key,value) -> {
						
						LOG.info("-------------------------------------------------");
						LOG.info("Nombre: " + key);
						LOG.info("Nombre Extendido: " + value);
						LOG.info("-------------------------------------------------");
						
					}
					
			);
			
		} else {
			LOG.error("El map es null o esta vacio");
		}
		
	}
	
	/**
	 * Metodo para crear un nombre de metodo set a partir del nombre del campo
	 * 
	 * @param field nombre del campo
	 * @return metodo set
	 */
	public String buildSetMethod(String field) {
		
		LOG.info("buildSetMethod");
		
		String setMethod = null;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if(field != null && !field.isEmpty()) {
			
			stringBuilder.append(PREFIX_SET);
			stringBuilder.append(field.substring(0, 1).toUpperCase());
			stringBuilder.append(field.substring(1, field.length()));
			
		} else {
			LOG.error("Campo no valido");
		}
		
		setMethod = stringBuilder.toString();
		
		LOG.info("metodo set generado " + setMethod);
		
		return setMethod;
		
	}
	
	public Map<String, Method> buildFieldMethodMap(Map<String, Field> fieldsMap, Map<String, Method> methodsMap){
		
		LOG.info("buildFieldMethodMap");
		
		Map<String, Method> fieldMethodMap = new TreeMap<String, Method>(); 
		
		if(fieldsMap != null && !fieldsMap.isEmpty() &&
				methodsMap != null && !methodsMap.isEmpty()) {
			
			for(Map.Entry<String, Field> entry : fieldsMap.entrySet()) {
				
				fieldMethodMap.put(entry.getKey(), methodsMap.get(buildSetMethod(entry.getKey())));
				
			}
			
		} else {
			LOG.error("Parametros no validos");
		}
		
		LOG.info("fieldMethodMap: " + fieldMethodMap);
		
		return fieldMethodMap;
		
	}

}
