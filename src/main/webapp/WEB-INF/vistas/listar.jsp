<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pacientes | Sistema de gestión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-primary">
    <div class="container"><span class="navbar-brand">Gestión de pacientes</span></div>
</nav>
<main class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div><h1 class="h3 mb-1">Pacientes registrados</h1><p class="text-muted mb-0">Administración de información</p></div>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/pacientes?accion=nuevo">+ Nuevo paciente</a>
    </div>
    <c:if test="${param.mensaje == 'guardado'}"><div class="alert alert-success">Registro guardado correctamente.</div></c:if>
    <c:if test="${param.mensaje == 'eliminado'}"><div class="alert alert-warning">Registro eliminado.</div></c:if>
    <div class="card shadow-sm"><div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary"><tr><th>ID</th><th>Paciente</th><th>DNI</th><th>Fecha nacimiento</th><th>Teléfono</th><th>Acciones</th></tr></thead>
            <tbody>
            <c:forEach var="p" items="${lista}">
                <tr>
                    <td><c:out value="${p.id}"/></td>
                    <td><c:out value="${p.nombres}"/> <c:out value="${p.apellidos}"/></td>
                    <td><c:out value="${p.dni}"/></td>
                    <td><c:out value="${p.fechaNacimiento}"/></td>
                    <td><c:out value="${p.telefono}"/></td>
                    <td class="text-nowrap">
                        <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/pacientes?accion=editar&id=${p.id}">Editar</a>
                        <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/pacientes?accion=eliminar&id=${p.id}" onclick="return confirm('¿Eliminar este paciente?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty lista}"><tr><td colspan="6" class="text-center text-muted py-4">No hay pacientes registrados.</td></tr></c:if>
            </tbody>
        </table>
    </div></div>
</main>
</body>
</html>
