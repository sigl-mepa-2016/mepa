<%@ include file="/WEB-INF/views/includes/common.jsp" %>

        <form class="/login" id="login-form">
                <div class="heading">Please sign in</div>
                         <label for="inputEmail" class="sr-only">Email address</label>
                         <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                         <label for="inputPassword" class="sr-only">Password</label>
                         <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                 <div class="checkbox">
                 <label>
                <input type="checkbox" value="remember-me"> Remember me
                 </label>
                </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>


       <!-- <form action="#" id="login-form">
          <div class="heading">Login to Everdwell</div>
          <div class="left">
            <label for="email">Email</label> <br />
            <input type="email" name="email" id="email" /> <br />
            <label for="password">Password</label> <br />
            <input type="password" name="password" id="pass" /> <br />
            <input type="submit" value="Login" />
          </div>-->