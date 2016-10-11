<!DOCTYPE HTML>
<html lang="en-GB">

    <head>
        @yield('meta')
        @yield('page_styles')
        @include('includes.header')
    </head>

    <body>

        @yield('content')
        
        @yield('scripts')
    </body>
</html>