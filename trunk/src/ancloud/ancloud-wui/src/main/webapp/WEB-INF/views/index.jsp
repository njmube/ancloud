<%@include file="/WEB-INF/views/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Le Tam An">
    
    <title>Ancloud</title>
    <link rel="icon" href="${imageResourcePath}/favicon.ico?v=1" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
        rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
        rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${resourcePath}/front.css?v=1">
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body id="page-top" class="skin-blue-light">
    <nav class="main-header navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" 
                        data-toggle="collapse" 
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Medtech</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse"
                id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="page-scroll" href="#about">About</a></li>
                    <li><a class="page-scroll" href="#services">Services</a></li>
                    <li><a class="page-scroll" href="#contact">Contact</a></li>
                    <li class="user-menu">
                        <c:if test="${not empty currentAccount}">
                            <a href="#"
                                class="dropdown-toggle"
                                data-toggle="dropdown"
                                style="color:#f05f40">
                                <img src="${imageResourcePath}/avatar.png"
                                    class="user-image"
                                    alt="User Image">
                                <span>${currentAccount.userName }</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="user-header"><img
                                    src="${imageResourcePath}/avatar.png"
                                    class="img-circle"
                                    alt="User Image">
                                    <p>
                                        ${currentAccount.userName } - 
                                        ${currentAccount.accountType }
                                        <small><bb-ex:message code="sc.sys.00020"></bb-ex:message>&nbsp;<bb-ex:formatDate jodaEnabled="true" jodaValue="${currentAccount.createdDate}" pattern="MMM. yyyy" /></small>
                                    </p>
                                </li>
                                <li class="user-body">
                                    <div class="row">
                                    </div>
                                </li>
                                <li class="user-footer">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li>
                                            <a href="${basePath}/admin/account/search">
                                                Go to admin page
                                            </a>
                                        </li>
                                        <li>
                                            <a href="${basePath}/logout">Logout</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </c:if>
                        <c:if test="${empty currentAccount}">
                            <a href="${basePath}/login">Login</a>
                        </c:if>
                    </li>
                </ul>
                
            </div>
            <!-- /.navbar-collapse -->
        </div>
    </nav>

    <header>
        <div class="header-content">
            <div class="header-content-inner">
                <h1 id="homeHeading">An favorite IoT solution for medical centers</h1>
                <hr>
                <p>Medtech can help you build better services using integrated IoT medical sensors around.</p>
                <a href="#about" class="btn btn-primary btn-xl page-scroll">Find Out More</a>
            </div>
        </div>
    </header>

    <section class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">We've got what you need!</h2>
                    <hr class="light">
                    <p class="text-faded">Medtech has excellent services you need
                        to get more accurate medical diagnostic actions, comfort and pleasure your patients. Buy Meditec's equipments and place them the right place. No hassle attached!</p>
                    <a href="#services" class="page-scroll btn btn-default btn-xl sr-button">Get Started!</a>
                </div>
            </div>
        </div>
    </section>

    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">At Your Service</h2>
                    <hr class="primary">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                
                <div class="col-lg-4 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa fa-4x fa-heartbeat text-primary sr-icons"></i>
                        <h3>Vital signs</h3>
                        <p class="text-muted">We provide you our excellent smart sensors to collect human vital signs, store and analyze.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa fa-4x fa-database text-primary sr-icons"></i>
                        <h3>Electronic health record</h3>
                        <p class="text-muted">Your data will be highly secure and safe.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 text-center">
                    <div class="service-box">
                        <i class="fa fa-4x fa-line-chart text-primary sr-icons"></i>
                        <h3>Healthcare analysis</h3>
                        <p class="text-muted">You can use our system to build dedicated forecast and trends. We have a reliable medical centers network with a huge amount of data.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">Let's Get In Touch!</h2>
                    <hr class="primary">
                    <p>Ready to start your business with us? That's great! Give
                        us a call or send us an email and we will get back to you as soon
                        as possible!</p>
                </div>
                <div class="col-lg-4 col-lg-offset-2 text-center">
                    <i class="fa fa-phone fa-3x sr-contact"></i>
                    <p>+65 123456</p>
                </div>
                <div class="col-lg-4 text-center">
                    <i class="fa fa-envelope-o fa-3x sr-contact"></i>
                    <p>
                        <a href="mailto:tamanle23@gmail.com">tamanle23@gmail.com</a>
                    </p>
                </div>
            </div>
        </div>
    </section>
    <script src="${resourcePath}/front.js"></script>
</body>
</html>
