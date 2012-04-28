package no.niths.application.rest;

import java.io.EOFException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import no.niths.application.rest.exception.BadRequestException;
import no.niths.application.rest.exception.CustomParseException;
import no.niths.application.rest.exception.HasNotRoleException;
import no.niths.application.rest.exception.InvalidValueException;
import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.exception.UnvalidEmailException;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.QueryParameterException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * EXCEPTIONHANDLING
 * 
 * Throwing exceptions with custom error header as a response
 * 
 */

public class RESTExceptionHandler {

	protected static final String ERROR = "Error";

	private static final Logger logger = LoggerFactory
			.getLogger(RESTExceptionHandler.class);

	/**
	 * PUT - Error with header parameters
	 * 
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void constraintViolation(ConstraintViolationException cve,
			HttpServletResponse res) {
		logger.debug("hibernate.constraintvia");
		res.setHeader(ERROR, cve.getMessage().toString());
	}

	/**
	 * PUT - Error with header parameters
	 * 
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public void constraintViolation2(
			javax.validation.ConstraintViolationException cve,
			HttpServletResponse res) {
		logger.debug("javax.constraint");
		String error = "";
		Set<ConstraintViolation<?>> constr = cve.getConstraintViolations();
		for (Iterator<ConstraintViolation<?>> iterator = constr.iterator(); iterator
				.hasNext();) {
			ConstraintViolation<?> constraintViolation = (ConstraintViolation<?>) iterator
					.next();
			error += constraintViolation.getPropertyPath() + " "
					+ constraintViolation.getMessage() + " ";
		}
		if (error.equals("")) {
			error = cve.getMessage();
		}
		res.setHeader(ERROR, error);
	}

	/**
	 * POST- Error with header parameters
	 * 
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void dataIntegrity(DataIntegrityViolationException e,
			HttpServletResponse res) {
		String error = e.getMessage().toString();
		error = error.replaceAll("; SQL .*", "");
		logger.debug("data");
		res.setHeader(ERROR, error);
	}

	/**
	 * When server fetches an object and try to insert it into an collection
	 * where the object already is Example: niths/committees/addEvent/1/5
	 */
	@ExceptionHandler(org.hibernate.NonUniqueObjectException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Sorry, it is already a member of the collection")
	public void notUniqueObjectEx(NonUniqueObjectException e,
			HttpServletResponse res) {
		res.setHeader(ERROR, e.getMessage().toString());
	}

	/**
	 * Catches illegal arguments Example: When you try to insert a subject into
	 * a committee
	 */
	@ExceptionHandler(java.lang.IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public void notValidParams(java.lang.IllegalArgumentException e,
			HttpServletResponse res) {
		res.setHeader(ERROR, e.getMessage().toString());
	}

	/**
	 * Catches access denied exceptions ExpiredTokenException,
	 * InvalidTokenException etc...
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void objectNotFound(ObjectNotFoundException e,
			HttpServletResponse res) {
		logger.debug("Object not found AbstractRestController");
		res.setHeader(ERROR, e.getMessage().toString());
	}

	/**
	 * Catches invalid email exceptions
	 */
	@ExceptionHandler(UnvalidEmailException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public void unvalidEmailException(UnvalidEmailException e,
			HttpServletResponse res) {
		res.setHeader(ERROR, e.getMessage().toString());
	}

	/**
	 * Catches access denied exceptions ExpiredTokenException,
	 * InvalidTokenException etc...
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public void accessDenied(AccessDeniedException e, HttpServletResponse res) {
		if (e.getMessage() == null) {
			res.setHeader(ERROR, "Access denied");
		}
		logger.debug("Access denied cathed in AbstractRestController");
	}

	/**
	 * Catches QueryParameterException, invalid query
	 */
	@ExceptionHandler(QueryParameterException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public void invalidSearchParam(QueryParameterException e,
			HttpServletResponse res) {
		res.setHeader(ERROR, "Invalid Search param");
		logger.debug("Invalid search param");
	}

	/**
	 * 
	 * @param e
	 * @param res
	 */
	@ExceptionHandler(EOFException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void endOfFile(EOFException e, HttpServletResponse res) {
		res.setHeader(ERROR, "Wrong input");
	}

	/**
	 * 
	 * @param e
	 * @param res
	 */
	@ExceptionHandler(HasNotRoleException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public void hasNotRole(HasNotRoleException e, HttpServletResponse res) {
		if (e.getMessage() == null) {
			res.setHeader(ERROR, "Does not have role");
		} else {
			res.setHeader(ERROR, e.getMessage());

		}
	}

	/**
	 * 
	 * @param e
	 * @param res
	 */
	@ExceptionHandler(NotInCollectionException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void notInCollectionException(NotInCollectionException e,
			HttpServletResponse res) {
		if (e.getMessage() == null) {
			res.setHeader(ERROR, "NotInCollectionException");
		} else {
			res.setHeader(ERROR, e.getMessage());
		}
		logger.debug("NotInCollectionException");
	}

	/**
	 * 
	 * @param e
	 * @param res
	 */
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void typeMismatchException(TypeMismatchException e,
			HttpServletResponse res) {
		if (e.getMessage() == null) {
			res.setHeader(ERROR, "TypeMismatchException");
		} else {
			res.setHeader(ERROR, e.getMessage());
		}
		logger.debug("TypeMismatchException");
	}

	/**
	 * 
	 * @param e
	 * @param res
	 */
	@ExceptionHandler(HibernateOptimisticLockingFailureException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void staleEx(HibernateOptimisticLockingFailureException e,
			HttpServletResponse res) {
		res.setHeader(ERROR,
				"Something went wrong, are the parameters correct?");
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void handleBindException(BindException e, HttpServletResponse res) {
		res.setHeader(ERROR, "You have provided a query string in which there"
				+ "are nested attribute(s)");
	}

	@ExceptionHandler(CustomParseException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public void customParseException(CustomParseException e,
			HttpServletResponse res) {
		if (e.getMessage() == null) {
			res.setHeader(ERROR, "CustomParseException");
		} else {
			res.setHeader(ERROR, e.getMessage());
		}
		logger.debug("CustomParseException");
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void handleBadRequestException(BadRequestException e,
			HttpServletResponse res) {
		res.setHeader(ERROR, e.getMessage());
	}

	@ExceptionHandler(ObjectInCollectionException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void objectInCollectionException(ObjectInCollectionException e,
			HttpServletResponse res) {
		if (e.getMessage() == null) {
			res.setHeader(ERROR, "ObjectInCollectionException");
		} else {
			res.setHeader(ERROR, e.getMessage());
		}
		logger.debug("ObjectInCollectionException");
	}

	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void notReadable(
			org.springframework.http.converter.HttpMessageNotReadableException cve,
			HttpServletResponse res) {
		res.setHeader("Error", cve.getMessage());
	}
	
	
	@ExceptionHandler(InvalidValueException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void invalidValueException(
			InvalidValueException cve,
			HttpServletResponse res) {
		res.setHeader("Error", cve.getMessage());
	}

}
