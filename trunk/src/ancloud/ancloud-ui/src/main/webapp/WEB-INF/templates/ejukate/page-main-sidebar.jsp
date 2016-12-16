<aside class="main-sidebar">
	<section class="sidebar">
		<form
			action="#"
			method="get"
			class="sidebar-form">
			<div class="input-group">
				<input
					type="text"
					name="q"
					class="form-control"
					placeholder="Search..."> 
				<span class="input-group-btn">
					<button
						type="submit"
						name="search"
						id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<bb-ex:navigationLink items="${sessionScope.NAVIGATION_LINKS}" type="sidebar"></bb-ex:navigationLink>

		<ul class="sidebar-menu animated-dropdown-menu" style="top:0px;">
			<li class="treeview active">
				<a href="javascript:void();"> 
					<i class="fa fa-gears"></i> 
					<span>Reload language</span>
				</a>
<!-- 				<ul class="treeview-menu animated-dropdown-menu"> -->
<!-- 					<li class=""><a href="/ancloud-ui/account"> <i -->
<!-- 							class="fa fa-user"></i> <span>Account management</span> -->
<!-- 					</a></li> -->
<!-- 					<li class=""><a href="/ancloud-ui/message"> <i -->
<!-- 							class="fa fa-database"></i> <span>Message</span> -->
<!-- 					</a></li> -->
<!-- 					<li class="active"><a href="/ancloud-ui/navigation-link"> -->
<!-- 							<i class="fa fa-link"></i> <span>Navigation link</span> -->
<!-- 					</a></li> -->
<!-- 				</ul> -->
			</li>
		</ul>
	</section>
</aside>