<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            color: #333;
        }

        header {
            background-color: #4CAF50;
            color: white;
            padding: 1rem;
            text-align: center;
        }

        .container {
            max-width: 800px;
            margin: 2rem auto;
            padding: 1rem;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .video-container {
            text-align: center;
            margin: 1rem 0;
        }

        video {
            width: 100%;
            border-radius: 10px;
        }

        .description {
            margin: 1rem 0;
            line-height: 1.6;
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 1rem;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .btn {
            display: inline-block;
            padding: 0.5rem 1rem;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 1rem;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .image-container img {
            width: 100%;
            border-radius: 10px;
            margin-top: 1rem;
        }

        .comments-section {
            margin-top: 2rem;
        }

        .comments-section h3 {
            margin-bottom: 1rem;
        }

        .comment {
            margin-bottom: 1rem;
        }

        .comment p {
            margin: 0.5rem 0;
        }

        .comment span {
            font-size: 0.9rem;
            color: #777;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to the Video Page</h1>
    </header>

    <div class="container">
        <div class="video-container">
            <video id="video" controls>
                <source src="https://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4">
                Your browser does not support the video tag.
            </video>
        </div>

        <div class="description">
            <h2>About This Video</h2>
            <p>This is an example video loaded from the server. You can watch and enjoy the content. Below you will find additional information and features related to this video.</p>
            <a href="#" class="btn">Learn More</a>
        </div>

        <div class="image-container">
            <h2>Related Image</h2>
            <img src="https://via.placeholder.com/800x400" alt="Placeholder Image">
        </div>

        <div class="comments-section">
            <h3>Comments</h3>
            <div class="comment">
                <p><strong>John Doe:</strong> This is an amazing video!</p>
                <span>Posted on July 20, 2024</span>
            </div>
            <div class="comment">
                <p><strong>Jane Smith:</strong> I found this video very informative. Thanks for sharing!</p>
                <span>Posted on July 21, 2024</span>
            </div>
        </div>
    </div>

    <footer>
        &copy; 2024 Video Page. All rights reserved.
    </footer>

    <script>
        // Function to fetch and display video
        async function fetchAndDisplayVideo() {
            try {
                // Replace with your actual URL
                const response = await fetch('Prtc?id=<%=request.getParameter("id")%>');
                
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                // Parse JSON response
                const data = await response.json();
                
                // Extract the base64 video string
                const base64Video = data.video;

                // Convert base64 string to binary data
                const binaryString = atob(base64Video);
                const binaryLen = binaryString.length;
                const bytes = new Uint8Array(binaryLen);
                for (let i = 0; i < binaryLen; i++) {
                    bytes[i] = binaryString.charCodeAt(i);
                }

                // Create a Blob from the byte array
                const videoBlob = new Blob([bytes], { type: 'video/mp4' });
                
                // Create an object URL for the Blob
                const videoUrl = URL.createObjectURL(videoBlob);
                
                // Set the src of the video element
                const videoElement = document.getElementById('video');
                videoElement.src = videoUrl;

                console.log('Video displayed successfully');
            } catch (error) {
                console.error('Error fetching or displaying video:', error);
            }
        }

        // Call the function to fetch and display the video
        fetchAndDisplayVideo();
    </script>
</body>
</html>
