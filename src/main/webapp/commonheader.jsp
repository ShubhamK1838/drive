    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

    
     <script>
        window.addEventListener('beforeunload', function (event) {
            // Execute custom function
            executeOnClose();
			
            return true; // For some browsers
        });

        function executeOnClose() {
            fetch("http://localhost:8080/drive/TestOne?user_id=102")
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    console.log("We got the response");
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });

        }
    </script>