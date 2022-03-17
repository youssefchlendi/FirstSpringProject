<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!--    <meta charset="UTF-8"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript"
	src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<title>Liste Produits</title>
</head>
<body>
	<div class="container">
		<div class="row my-4">
			<div class="col">
				<h1 class="text-center">Liste des Produits</h1>
				<a href="showCreate" class="float-start btn btn-success"> <i
					class="fa-solid fa-plus"></i> Ajouter
				</a>
			</div>
		</div>
		<c:if test="${not empty msg}">
			<div class="my-4 alert alert-${type}" role="alert">${msg}</div>
		</c:if>
		<div class="row my-4">
			<table class="table table-dark table-striped">
				<thead>
					<th>ID</th>
					<th>Nom Produit</th>
					<th>Prix</th>
					<th>Date Création</th>
					<th>Actions</th>
				</thead>
				<c:forEach items="${produits}" var="p">
					<tr>
						<td>${p.idProduit }</td>
						<td>${p.nomProduit }</td>
						<td>${p.prixProduit }</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${p.dateCreation}" /></td>
						<td><a class="btn btn-danger "
							onclick="return confirm('Etes-vous sur ?')"
							href="supprimerProduit?id=${p.idProduit }"><i
								class="fa-solid fa-trash"></i></a> <a class="btn btn-warning "
							href="modifierProduit?id=${p.idProduit }"><i
								class="fa-solid fa-pen"></i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>