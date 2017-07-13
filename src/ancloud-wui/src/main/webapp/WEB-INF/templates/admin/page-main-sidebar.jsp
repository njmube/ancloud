<%@include file="/WEB-INF/views/include.jsp" %>

<aside class="main-sidebar">
    <section class="sidebar">
<!--        <form -->
<!--            action="#" -->
<!--            method="get" -->
<!--            class="sidebar-form"> -->
<!--            <div class="input-group"> -->
<!--                <input -->
<!--                    type="text" -->
<!--                    name="q" -->
<!--                    class="form-control" -->
<!--                    placeholder="Search...">  -->
<!--                <span class="input-group-btn"> -->
<!--                    <button -->
<!--                        type="submit" -->
<!--                        name="search" -->
<!--                        id="search-btn" -->
<!--                        class="btn btn-flat"> -->
<!--                        <i class="fa fa-search"></i> -->
<!--                    </button> -->
<!--                </span> -->
<!--            </div> -->
<!--        </form> -->
        <ul class="sidebar-menu animated-dropdown-menu">
            <li>
                <a href="${basePath }/admin/dashboard"> 
                    <i class="sidebar-menu-icon bb bb-dashboard"></i> 
                    <span>Dashboard</span> 
                </a>
            </li>
<%--        <sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')">    --%>
            <li class="treeview">
                <a href="#"> 
                    <i class="sidebar-menu-icon bb bb-hospital"></i> 
                    <span>CMS</span> 
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu menu-open" style="display: block;">
<%--                    <sec:authorize access="hasAnyRole('Administrator,Doctor,Nurse')"> --%>
                    <li>
                        <a href="${basePath }/admin/post/search">
                            Post
                        </a>
                    </li>
                </ul>
            </li>
<%--             <sec:authorize access="hasRole('Administrator')"> --%>
                <li class="treeview">
                    <a href="#"> 
                        <i class="sidebar-menu-icon bb bb-team"></i> 
                        <span>Administration</span> 
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu menu-open" style="display: block;">
<%--                        <sec:authorize access="hasAuthority('account_search')"> --%>
                            <li>
                                <a href="${basePath }/admin/account/search">
                                    User
                                </a>
                            </li>
<%--                        </sec:authorize> --%>
                        <li>
                            <a href="${basePath }/admin/account-license/search">
                                License
                            </a>
                        </li>
<%--                        <sec:authorize access="hasAuthority('account_search')"> --%>
                            <li>
                                <a href="${basePath }/admin/account-permission/search">
                                Permission
                                </a>
                            </li>
<%--                        </sec:authorize> --%>
                    </ul>
                </li>
<%--             </sec:authorize> --%>
        </ul>
    </section>
</aside>