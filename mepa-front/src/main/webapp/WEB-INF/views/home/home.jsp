<%@ include file="/WEB-INF/views/includes/common.jsp"%>


<div class="container">

    <!-- Home message-->
        <div class="welcome-content">
                    <div class="welcome-content-inner">
                        <h1>WELCOME TO EpiData!</h1>
                        <hr>
                        <p>Data accessible and useable by everyone</p>
                    </div>
                </div>

    <!-- Exemple button to delete-->
        <!--<p class="text-center">
            <c:url var="coreExampleUrl" value="/example/core/"/>
            <a class="btn btn-lg btn-primary" href="${coreExampleUrl}" role="button">View Core (Database) module and form validation example</a>
        </p>-->

    <!-- Search bar to change-->
        <div align="center">
            <form>
        		<input type="text" size="21" placeholder="Find Data"><input type="submit" value="search" class="submit-button">
        	</form>
         </div>

    <!-- Exemple cat image to delete-->
        <!--<p class="text-center well-done">
            <c:url var="wellDoneImgUrl" value="/img/welldone.jpg"/>
             <img src="${wellDoneImgUrl}" alt=""/>
        </p>-->

</div>
