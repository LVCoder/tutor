    <header id = "mainHeader">
        <img src="resources/images/logoWhite.svg" alt="logo">
        <form ng-submit="searchUsers();"  id = "search" ng-controller="SearchController">
            <input type="text" ng-model="search_query">
            <button >Search</button >
        </form>
        
    </header>
    <nav id  = "mainNav">
            <div class="leftSide">
                <a href="">Dashboard</a>
                <a href="#/">Profile</a>
                <a href="">Payments</a>
                <a href="" ng-click="logout();">Log out</a>
            </div>
            <div class="rightSide">
                <a href=""><img src="" alt=""></a>
                <a href=""><img src="" alt=""></a>
                <a href=""><img src="" alt=""></a>
            </div>
        </nav>