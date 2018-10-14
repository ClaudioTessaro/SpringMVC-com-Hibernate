<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- essa tag lib serve para trabalhar com formulario de cadastro -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuários</title>
<spring:url var="css" value="/static/css/bootstrap.css"/>
<link type="text/css" rel="stylesheet" href="${css}"/>
</head>
<body>
	<div class="container">
		<h1>Cadastro de Usuários</h1>
		<hr>
		<div>
			<spring:url value="/usuario/todos" var="home"/>
			<a class="btn btn-default" href="${home}">Home</a>
		</div>
		<hr>
		<div>
			<spring:url value="${usuario.id==null ? '/usuario/save' : '/usuario/update'}" var="save" />
			<form:form modelAttribute="usuario" action="${save }" method="post">
				<form:hidden path="id"/>
				<!-- esse model atribute serve para mostrar a classe de dominio, ou seja, o nome e sobrenome fazem parte da classe usuario -->
				<div class="form-group">
					<label for="name">Nome:</label>
					<form:input path="nome" class="form-control" />
					<form:errors path="nome" cssClass="label labe-danger"/>
				</div>
				<div class="form-group">
					<label for="sobrenome">Sobrenome:</label>
					<form:input path="sobrenome" class="form-control" />
					<form:errors path="sobrenome" cssClass="label labe-danger"/>
				</div>
				<div class="form-group">
					<label for="dtNascimento">Data Nascimento:</label>
					<form:input path="dtNascimento" class="form-control" type="date" />
					<form:errors path="dtNascimento" cssClass="label labe-danger"/>
				</div>
				<div class="form-group">
					<label for="sexo">Sexo:</label>
					<form:select path="sexo" class="form-control">
						<form:options items="${sexos }" itemLabel="desc"/>
					</form:select>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Confirmar</button>
				</div>
			</form:form>
		</div>
		<hr>
		<footer class="footer">
		<p>&copy; 2018 Estudando</p>
		</footer>

	</div>
</body>
</html>