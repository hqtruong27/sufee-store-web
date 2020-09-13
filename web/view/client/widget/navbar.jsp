<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <div style="">
        <section id="navigation">
            <div class="container">
                <div id="responsive-nav">
                    <div class="category-nav" >
                        <ul class="category-list">
                            <c:if test="${not empty navHtml}">${navHtml}</c:if>
                            </ul>
                        </div>

                    </div>
                </div>
            </section>
        </div>

    </div>
</div>