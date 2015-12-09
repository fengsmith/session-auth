<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>
<script>
    var ctx = "${pageContext.request.contextPath}";
    $(document).ajaxSuccess(function(event, request, settings) {
        if (request.getResponseHeader('loginFlag')) {
            parent.location.href = ctx + "/preLogin";
        }
    });
</script>
