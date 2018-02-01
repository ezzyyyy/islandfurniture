<%@page import="java.util.List"%>
<%@page import="EntityManager.StoreEntity"%>
<jsp:include page="checkCountry.jsp" />
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <div class="body">
            <jsp:include page="menu2.jsp" />

            <div role="main" class="main">
                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>Store Locations</h2>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="container">

                    <div class="row">
                        <div class="col-md-12">

                            <div class="toogle" data-plugin-toggle>
                                <%
                                    List<StoreEntity> stores = (List<StoreEntity>) (session.getAttribute("storesInCountry"));
                                    try {
                                        if (stores != null) {
                                            for (int i = 0; i < stores.size(); i++) {
                                %>
<script>
    function UpdateIFrame(){
//        console.log('running function');
        $.ajax({
        url: 'https://developers.onemap.sg/commonapi/search?searchVal='+'<%=stores.get(i).getAddress()%>'+'&returnGeom=Y&getAddrDetails=N',
        success: function(result){
            //console.log(result);
            //console.log(result.results[0]);
            Lat = result.results[0].LATITUDE;
            Long = result.results[0].LONGITUDE;
            //console.log(Lat+','+Long + '<%=stores.get(i).getName()%>');
            newSrc = 'https://tools.onemap.sg/minimap/minimap.html?mWidth=490&mHeight=195&latLng='+Lat+','+Long+'&zoomLevl=17&iwt='+
                                                    '<span><%=stores.get(i).getName()%></span><br>'+
                                                    '<div><span>Address: <%=stores.get(i).getAddress()%></span><br style="box-sizing: border-box;">'+
                                                    '<span>Telephone: <%=stores.get(i).getTelephone()%></span><br style="box-sizing: border-box;">'+
                                                    '<span>Email: <%=stores.get(i).getEmail()%></span>'+
                                                    '</div>&popupWidth=200&popupHeight=500&includePopup=true&onloadPopup=true&design=default';
            //console.log(newSrc);
            document.getElementById('oneMap<%=i%>').src = newSrc;
            //adding < % = i % > solved the problem as the element by ID does not know which element to get.
        }});
    }
</script>
                                <section class="toggle">
                                    <label>Name: <%=stores.get(i).getName()%></label>
                                    <div class="toggle-content">
                                        <div class="col-md-6">
                                            <div id="map_canvas">
                                                <script>UpdateIFrame();</script>
                                                <iframe id='oneMap<%=i%>' src='https://tools.onemap.sg/minimap/minimap.html?mWidth=490&mHeight=195&latLng=1.28794376443444,103.806003412297&zoomLevl=17&iwt=
                                                    <span><%=stores.get(i).getName()%></span><br>
                                                    <div><span>Address: <%=stores.get(i).getAddress()%></span><br style="box-sizing: border-box;">
                                                    <span>Telephone: <%=stores.get(i).getTelephone()%></span><br style="box-sizing: border-box;">
                                                    <span>Email: <%=stores.get(i).getEmail()%></span>
                                                    </div>&popupWidth=200&popupHeight=500&includePopup=true&onloadPopup=true&design=default' height=200px width=500px scrolling='no' frameborder='0' allowfullscreen='allowfullscreen'>
                                                </iframe>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <p>
                                                Address: <%=stores.get(i).getAddress()%><br/>
                                                Telephone: <%=stores.get(i).getTelephone()%><br/>
                                                Email: <%=stores.get(i).getEmail()%><br/>
                                            </p>
                                        </div>
                                        
                                    </div>
                                </section>

                                <%
                                            }
                                        }
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                %>

                            </div>
                        </div>

                    </div>
                </div>
                <jsp:include page="footer.html" />

                <!-- Theme Initializer -->
                <script src="../../js/theme.plugins.js"></script>
                <script src="../../js/theme.js"></script>

                <!-- Current Page JS -->
                <script src="../../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
                <script src="../../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
                <script src="../../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
                <script src="../../js/views/view.home.js"></script>
            </div>
    </body>
</html>