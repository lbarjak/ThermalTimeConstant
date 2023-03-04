class MyClass {

	constructor() {
		console.log("Hello!!");

		console.log("indexOfMeasuredTemperatures:");
		console.log(indexOfMeasuredTemperatures);
		console.log("roomTemp1:");
		console.log(roomTemp1);
		console.log("outdoorTemp:");
		console.log(outdoorTemp);
		console.log("forecast:");
		console.log(forecast);
		console.log("startTime:");
		console.log(startTime);
	}

}

new MyClass();

new Chart(document.getElementById("line-chart"), {
	type: 'line',
	data: {
		labels: roomTemp1,
		datasets: [
			{
				data: forecast,
				label: "Előrejelzés előző 24h átlagból",
				borderColor: "#c45850",
				fill: false
			},
			{
				data: roomTemp1,
				label: "Számítás termikus időállandóval",
				borderColor: "#3e95cd",
				fill: false
			},
			{
				data: outdoorTemp,
				label: "Mért adatokból",
				borderColor: "#8e5ea2",
				fill: false
			}
		]
	}
});