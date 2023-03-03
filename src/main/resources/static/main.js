class MyClass {

	constructor() {
		console.log("Hello!!");

		console.log("indexOfMeasuredTemperatures:");
		console.log(indexOfMeasuredTemperatures);
		console.log("roomTemp1:");
		console.log(roomTemp1);
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
				data: roomTemp1,
				label: "Mért adatokból",
				borderColor: "#3e95cd",
				fill: false
			}]
	}
});