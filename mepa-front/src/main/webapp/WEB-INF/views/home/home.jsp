<%@ include file="/WEB-INF/views/includes/common.jsp"%>

<div class="container">
    <div class="jumbotron">

        <h1>Hello SIGL, this is the home page!</h1>

        <p class="alert alert-success">Seems to be working? Good!</p>

        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget mauris a lacus commodo placerat.
            Pellentesque commodo, nisl mollis cursus ultrices, dui tellus molestie purus, ac lobortis lectus elit ac mi.
            Pellentesque tristique nisi nec massa auctor in interdum nisl mattis. Aenean convallis dignissim eleifend. Morbi
            quis tortor odio. Quisque at lectus sed arcu consectetur venenatis ac nec sem. Nulla vehicula eleifend iaculis.
            Donec libero purus, aliquet et volutpat porta, bibendum vel enim. Proin tempus rutrum iaculis. Proin vulputate
            dignissim lobortis. Aenean ante elit, condimentum sed fringilla nec, sagittis in magna.</p>

        <p class="text-center">
            <c:url var="coreExampleUrl" value="/example/core/"/>
            <a class="btn btn-lg btn-primary" href="${coreExampleUrl}" role="button">View Core (Database) module and form validation example</a>
        </p>

        <p class="text-center well-done">
            <c:url var="wellDoneImgUrl" value="/img/welldone.jpg"/>
             <img src="${wellDoneImgUrl}" alt=""/>
        </p>
    </div>
</div>