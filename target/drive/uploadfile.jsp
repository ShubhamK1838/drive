<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Upload File</title>
<%@include file="commonheader.jsp"%>
<style>
    #progressBarContainer {
        width: 100%;
        background-color: #f3f3f3;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 20px;
    }
    #progressBar {
        width: 0%;
        height: 30px;
        background-color: #4caf50;
        text-align: center;
        line-height: 30px;
        color: white;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <main class="main-content" style="display: flex; justify-content: center;">
        <div class="container" id="main">
            <h2>Upload File</h2>
            <form id="uploadForm" enctype="multipart/form-data" class="form">
                <label for="file">Choose file to upload</label>
                <input type="file" id="file" name="file" required>
                <button type="button" class="btn" onclick="uploadFile()">Upload</button>
            </form>
            
        </div>
        <div class="container" style="display: none" id="sub">
            <jsp:include page="datasend.jsp" />
            <br>
            <p>Please Wait While We Upload File...
            
            </p>
            <div id="progressBarContainer" style="display:none;">
                <div id="progressBar">0%</div>
            </div>
        </div>
    </main>

    <script>
        function uploadFile() {
            var form = document.getElementById('uploadForm');
            var formData = new FormData(form);
            var xhr = new XMLHttpRequest();

            xhr.upload.addEventListener('progress', function(e) {
                var percent = (e.loaded / e.total) * 100;
                var progressBar = document.getElementById('progressBar');
                progressBar.style.width = percent + '%';
                progressBar.textContent = Math.round(percent) + '%';
            });

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4) {
                    /* if (xhr.status == 200) {
                        alert('File uploaded successfully!');
                    } else {
                        alert('File upload failed.');
                    } */
                    resetUploadForm();
                }
            };

            xhr.open('POST', 'SaveFile', true);
            xhr.send(formData);

            document.getElementById('main').style.display = 'none';
            document.getElementById('sub').style.display = 'inline-block'; 
            document.getElementById('progressBarContainer').style.display = 'block'; 
        }

        function resetUploadForm() {
            // Reset form fields
            document.getElementById('uploadForm').reset();

            // Reset progress bar
            document.getElementById('progressBar').style.width = '0%';
            document.getElementById('progressBar').textContent = '0%';

            // Hide progress bar container and show main content
            document.getElementById('progressBarContainer').style.display = 'none';
            document.getElementById('main').style.display = 'block';
            document.getElementById('sub').style.display = 'none';
        }
    </script>
</body>
</html>


<%--  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Upload File</title>
<%@include file="commonheader.jsp"%>
<style>
    #progressBarContainer {
        width: 100%;
        background-color: #f3f3f3;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-top: 20px;
    }
    #progressBar {
        width: 0%;
        height: 30px;
        background-color: #4caf50;
        text-align: center;
        line-height: 30px;
        color: white;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <main class="main-content" style="display: flex; justify-content: center;">
        <div class="container" id="main">
            <h2>Upload File</h2>
            <form id="uploadForm" enctype="multipart/form-data" class="form">
                <label for="file">Choose file to upload</label>
                <input type="file" id="file" name="file" required>
                <button type="button" class="btn" onclick="uploadFile()">Upload</button>
            </form>
            
        </div>
        <div class="container" style="display: none" id="sub">
            <jsp:include page="datasend.jsp" />
            <br>
            <p>Please Wait While We Upload File...
            
            </p>
            <div id="progressBarContainer" style="display:none;">
                <div id="progressBar">0%</div>
            </div>
        </div>
    </main>

    <script>
        function uploadFile() {
            var form = document.getElementById('uploadForm');
            var formData = new FormData(form);
            var xhr = new XMLHttpRequest();

            xhr.upload.addEventListener('progress', function(e) {
                var percent = (e.loaded / e.total) * 100;
                var progressBar = document.getElementById('progressBar');
                progressBar.style.width = percent + '%';
                progressBar.textContent = Math.round(percent) + '%';
            });

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    alert('File uploaded successfully!');
                    document.getElementById('progressBar').style.width = '0%';
                    document.getElementById('progressBar').textContent = '0%';
                }
            };

            xhr.open('POST', 'SaveFile', true);
            xhr.send(formData);

            document.getElementById('main').style.display = 'none';
           document.getElementById('sub').style.display = 'inline-block'; 
            document.getElementById('progressBarContainer').style.display = 'block'; 
        }
    </script>
</body>
</html>
 --%>