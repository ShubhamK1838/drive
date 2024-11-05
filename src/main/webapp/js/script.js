async function fetchAndDisplayVideo(id, videoEle) {
	try {
		
		const response = await fetch('Prtc?id=' + id);

		if (!response.ok) {
			throw new Error('Network response was not ok');
		}


		const data = await response.json();

	
		const base64Video = data.video;


		const binaryString = atob(base64Video);
		const binaryLen = binaryString.length;
		const bytes = new Uint8Array(binaryLen);
		for (let i = 0; i < binaryLen; i++) {
			bytes[i] = binaryString.charCodeAt(i);
		}

	
		const videoBlob = new Blob([bytes], { type: 'video/mp4' });

	
		const videoUrl = URL.createObjectURL(videoBlob);

	

		videoEle.src = videoUrl;

		console.log('Video displayed successfully');
	} catch (error) {
		console.error('Error fetching or displaying video:', error);
	}
}


document.addEventListener('DOMContentLoaded', function() {
	fetch('data')
		.then(response => response.json())
		.then(data => {
			displayFiles(data);
		})
		.catch(error => console.error('Error fetching data:', error));
});
function displayFiles(data) {
	const container = document.getElementById('file-container');
	if (!container) {
		console.error('File container element not found.');
		return;
	}

	container.innerHTML = '';

	data.forEach(file => {
		const fileItem = document.createElement('div');
		fileItem.className = 'file-item';

		const fileName = document.createElement('h3');
		fileName.textContent = file.name;

		const fileType = document.createElement('p');
		fileType.textContent = `Type: ${file.type}`;

		const addedDate = document.createElement('p');
		addedDate.textContent = `Added Date: ${file.addedDate}`;

		const fileSize = document.createElement('p');
		fileSize.textContent = `Size: ${file.size}`;

		const actions = document.createElement('div');
		actions.className = 'actions';

		const shareButton = document.createElement('button');
		shareButton.textContent = 'Share';
		actions.appendChild(shareButton);

		const deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete';
		actions.appendChild(deleteButton);

		const downloadButton = document.createElement('button');
		downloadButton.textContent = 'Download';
		downloadButton.addEventListener('click', () => {
			window.location.href = `/file?file=${encodeURIComponent(file.url)}`;
		});
		actions.appendChild(downloadButton);

		if (file.type === 'image') {
			const image = document.createElement('img');
			/* image.src = `data:image/png;base64,${file.data}`; // Use base64 data*/
			image.src = file.url; // Use base64 data
			image.alt = file.name;
			image.className = 'file-content image-content';
			fileItem.appendChild(image);
		} else if (file.type === 'video') {
			const video = document.createElement('video');
			/* video.src = file.url;*/
			video.controls = true;
			video.className = 'file-content video-content';
			fetchAndDisplayVideo(file.id, video);
			fileItem.appendChild(video);
		} else if (file.type === 'exe') {
			const exeImage = document.createElement('img');
			exeImage.src = 'img/show.png'; // Replace with the path to your .exe icon image
			exeImage.alt = file.name;
			exeImage.className = 'file-content exe-content';
			fileItem.appendChild(exeImage);
		}

		fileItem.appendChild(fileName);
		fileItem.appendChild(fileType);
		fileItem.appendChild(addedDate);
		fileItem.appendChild(fileSize);
		fileItem.appendChild(actions);

		container.appendChild(fileItem);
	});
}

