<%@page contentType="text/html" pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">

    <title>
      
        StartUp &middot; Marketing theme &middot; Official Bootstrap Themes
      
    </title>

    
      <link href='https://fonts.googleapis.com/css?family=Lora:400,400italic|Work+Sans:300,400,500,600' rel='stylesheet' type='text/css'>
      <link href="http://bootstrap-themes.github.io/marketing/assets/css/toolkit-startup.css" rel="stylesheet">
      <link href="http://bootstrap-themes.github.io/marketing/assets/css/application-startup.css" rel="stylesheet">
    

    

    <style>
      /* note: this is a hack for ios iframe for bootstrap themes shopify page */
      /* this chunk of css is not part of the toolkit :) */
      /* …curses ios, etc… */
      @media (max-width: 768px) and (-webkit-min-device-pixel-ratio: 2) {
        body {
          width: 1px;
          min-width: 100%;
          *width: 100%;
        }
        #stage {
          height: 1px;
          overflow: auto;
          min-height: 100vh;
          -webkit-overflow-scrolling: touch;
        }
      }
    </style>
  </head>


<body>
  



<div class="bsa bsb hidden" id="sidebar">
  <ul class="nav bpi nav-stacked yl">
    <li class="azb">Examples</li>
    <li class="m">
      <a class="qm active" href="index.html">Startup</a>
    </li>
    <li class="m">
      <a class="qm" href="minimal/index.html">Minimal</a>
    </li>
    <li class="m">
      <a class="qm" href="bold/index.html">Bold</a>
    </li>
    <li class="nav-divider"></li>
    <li class="azb">Docs</li>
    <li class="m">
      <a class="qm" href="docs/index.html">Toolkit</a>
    </li>
    <li class="m">
      <a class="qm" href="http://getbootstrap.com">Bootstrap</a>
    </li>
  </ul>
</div>

<div class="brz" id="stage">

<div class="k am bqa bse"
     style="background-image: url(assets/img/startup-1.jpg);">

  <div class="e aij aey app-navbar">
  <nav class="du bqq azg qz">
    <a class="l aiy" href="">
      <strong style="background: #fff; padding: 12px; border-radius: 4px; color: #28669F;">MoviesNow</strong>
    </a>
    <button
      class="qv"
      type="button"
      data-target="#stage"
      data-toggle="stage"
      data-distance="-250">
      <span class="qw"></span>
    </button>

    <div class="d-none wx axv">
      <ul class="navbar-nav">
        <li class="m ahq ">
          <a class="qm" href="./register.jsp">Register</a>
        </li>
      </ul>
    </div>
  </nav>
</div>

  <img class="z"  src="assets/img/startup-0.svg">

  <div class="bqg ait">
    <div class="e">
      <div class="c">
        <div class="fv gt">
          <h1 class="bsf bsg">The best movie store.</h1>
          <p class="al agw an">Join us now and try the most recent movies. It's worth the cost.</p>
          <a href="./login.jsp" style="color: #fff"><button class="dm ox ap">Log in</button></a>
        </div>
      </div>
    </div>
  </div>
</div>

</div>


    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/toolkit.js"></script>
    <script src="assets/js/application.js"></script>
    <script>
      // execute/clear BS loaders for docs
      $(function(){while(window.BS&&window.BS.loader&&window.BS.loader.length){(window.BS.loader.pop())()}})
    </script>
</body>
</html>

