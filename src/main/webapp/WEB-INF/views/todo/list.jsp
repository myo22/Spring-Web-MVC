<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- 기존의 <h1>Header</h1> -->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                                <a class="nav-link" href="#">Features</a>
                                <a class="nav-link" href="#">Pricing</a>
                                <a class="nav-link disabled">Disabled</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- header end -->
        <!-- 기존의 <h1>Header</h1>끝 -->

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Tno</th>
                                <th scope="col">Title</th>
                                <th scope="col">Writer</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${responseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.tno}"/></th>
                                    <td>
                                        <a href="/todo/read?tno=${dto.tno}" class="text-decoration-none">
                                            <c:out value="${dto.title}"/>
                                        </a>
                                    </td>
                                    <td><c:out value="${dto.writer}"/></td>
                                    <td><c:out value="${dto.dueDate}"/></td>
                                    <td><c:out value="${dto.finished}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start - 1}">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="${responseDTO.start}" end ="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num? "active":""} ">
                                        <a class="page-link" data-num="${num}">${num} </a>
                                    </li>
                                </c:forEach>

                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>

                        <script>

                            document.querySelector(".pagination").addEventListener("click", function (e) {
                                e.preventDefault()
                                e.stopPropagation()

                                const target = e.target

                                if (target.tagName !== 'A') { //<a> 태그를 클릭했을 때만 data-num 속성값을 읽어와서 현재 주소(self.location)를 변경하는 방식으로 작성
                                    return
                                }
                                const num = target.getAttribute("data-num") //위에서 'data-' 속성을 이용해서 필요한 속성을 추가한걸 여기서 쓰는 것이다.

                                self.location = `/todo/list?page=\${num}` //백틱(' ')를 이용해서 템플릿 처리 Why? 문자열 결합에 '+'를 이용해야 하는 불편함 줄임 대신 JSP의 EL이 아니라는 것을 표시하기 위해서 '\${}'로 처리}
                            },false)

                        </script>

                    </div>
                </div>
            </div>

    <div class="row footer">
        <!--<h1>Footer</h1>-->

        <div class="row   fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1 ">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>

    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>