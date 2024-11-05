<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Video from JSON</title>
</head>
<body>
    <h1>Video from JSON</h1>
    <video id="video" controls width="600">
        Your browser does not support the video tag.
    </video>

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
