
async function callApi() {
	//const res = await fetch("https://jsonplaceholder.typicode.com/users");
	//const users = await res.json();
	//console.log(users)
	//document.getElementById(users);
};
async function buttonClick2() {


	const url = '/result'; // POSTリクエストのURL

	// 送信するJSONデータ
	const data = {
		"ResultSet": {
			"apiVersion": "1.27.0.0",
			"engineVersion": "202501_02a",
			"Point": [
				{
					"Station": {
						"code": "22361",
						"Name": "千葉",
						"Type": "train",
						"Yomi": "ちば"
					},
					"Prefecture": {
						"code": "12",
						"Name": "千葉県"
					}
				},
				// 他のデータも同様に追加
			]
		}
	};

	// リクエストの設定
	const options = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	};

	// リクエストの送信
	fetch("/result", options)
		.then(response => response.json())
		.then(data2 => {
			console.log('Success:', data2);
		})
		.catch((error) => {
			console.error('Error:', error);
		});

}

function buttonClick2() {
	window.location.href = 'result.html';
}

function buttonClick4() {
	window.location.href = 'login.html';
}

function buttonClick5() {
	window.location.href = 'list.html';
}

function buttonClick6() {
	window.location.href = 'index.html';
}

//↓今は使わない  
async function buttonClick3() {
	//let element = document.getElementById('search');
	//window.location = "https://mixway.ekispert.jp/v1/json/station/light?key=test_QMMh4JZJgnC&name="+search.value;
	//p.innerHTML = "表示されました！"; 
	//https://mixway.ekispert.jp/v1/json/search/course/extreme?key=test_QMMh4JZJgnC&viaList=22361:22197&date=20241119&time=1510
	//window.location.href = "/result";
	var request = new XMLHttpRequest();
	var date = new Date();
	var time2;
	//request.open('GET',"https://mixway.ekispert.jp/v1/json/station/light?key=test_QMMh4JZJgnC&name="+search.value, true);
	const res = await fetch("https://mixway.ekispert.jp/v1/json/station/light?key=test_QMMh4JZJgnC&name=" + search2.value);
	//request.responseType = 'json';
	const data5 = await res.json();

	const res2 = await fetch("https://mixway.ekispert.jp/v1/json/station/light?key=test_QMMh4JZJgnC&name=" + search3.value);
	const data6 = await res2.json();

	let j2 = 0;

	console.log(data5)
	console.log(data6)
	//console.log(data2.ResultSet.Point[0].Station.Name)

	//完全検索化する
	if (data5.ResultSet.Point) {

		if (Array.isArray(data5.ResultSet.Point)) {
			for (let i = 0; i < data5.ResultSet.Point.length; i++) {
				if (data5.ResultSet.Point[i].Station.Name == search2.value) {
					var resu = data5.ResultSet.Point[i].Station.Name;
					var id = data5.ResultSet.Point[i].Station.code;
					break;
				} else {
					var resu = "undefined";
				}
			}
		} else {
			if (data5.ResultSet.Point.Station.Name == search2.value) {
				var resu = data5.ResultSet.Point.Station.Name;
				var id = data5.ResultSet.Point.Station.code;
			} else {
				var resu = "undefined";
			}
		}
	} else {
		var resu = "undefined";
	}

	if (data6.ResultSet.Point) {

		if (Array.isArray(data6.ResultSet.Point)) {
			for (let i = 0; i < data6.ResultSet.Point.length; i++) {
				if (data6.ResultSet.Point[i].Station.Name == search3.value) {
					var resu2 = data6.ResultSet.Point[i].Station.Name;
					var id2 = data6.ResultSet.Point[i].Station.code;
					break;
				} else {
					var resu2 = "undefined";
				}
			}
		} else {
			if (data6.ResultSet.Point.Station.Name == search3.value) {
				var resu2 = data6.ResultSet.Point.Station.Name;
				var id2 = data6.ResultSet.Point.Station.code;
			} else {
				var resu2 = "undefined";
			}
		}
	} else {
		var resu = "undefined";
	}


	var data3 = "<h2><center><検索結果></center></h2>";
	console.log(data3)
	if (resu == "undefined" || resu2 == "undefined") {
		data3 = "<strong>　ERROR 　</strong>記入した値に不正があります。";
		document.getElementById("result2").innerHTML = data3;
	} else {


		//window.location = "https://mixway.ekispert.jp/v1/json/search/course/extreme?key=test_QMMh4JZJgnC&viaList="+id+":"+id2+"&date=20241119&time=1510";
		const res3 = await fetch("https://mixway.ekispert.jp/v1/json/search/course/extreme?key=test_QMMh4JZJgnC&viaList=" + id + ":" + id2 + "&date=" + date.getFullYear() + ('00' + date.getMonth() + 1).slice(-2) + ('00' + date.getDate()).slice(-2) + "&time=" + ('00' + date.getHours()).slice(-2) + ('00' + date.getMinutes()).slice(-2));
		const data7 = await res3.json();
		console.log(data7)

		if (data7.ResultSet.Error) {
			data3 = "<strong>　ERROR 　</strong>記入した値に不正があります。";
			document.getElementById("result2").innerHTML = data3;
		}
		else if (data7.ResultSet.Course) {

			if (Array.isArray(data7.ResultSet.Course)) {

				//data3 += "・"+ data2.ResultSet.Point.length +"件ヒットしました。<br>"; 
				for (let i = 0; i < data7.ResultSet.Course.length; i++) {
					//count++;

					if (data7.ResultSet.Course[i].Teiki) {
						data3 += "<h2><div class='colorc'><font color='#ffffff'>▷ " + data7.ResultSet.Course[i].Teiki.DisplayRoute + "</font></div></h2>";
					} else {
						data3 += "<h2><div class='colorc'><font color='#ffffff'>▷ " + resu + "--" + resu2 + "</font></div></h2>";
					}

					for (let j = 0; j < data7.ResultSet.Course[i].Price.length; j++) {
						if (data7.ResultSet.Course[i].Price[j].kind == "FareSummary") {
							j2 = j;
							break;
						}
					}

					data3 += "　片道運賃:" + data7.ResultSet.Course[i].Price[j2].Oneway + "円　往復運賃:" + data7.ResultSet.Course[i].Price[j2].Round + "円<br>";
					if (data7.ResultSet.Course[i].Price[j2 + 5]) {
						data3 += "　定期1か月:" + data7.ResultSet.Course[i].Price[j2 + 1].Oneway + "円　定期3か月:" + data7.ResultSet.Course[i].Price[j2 + 3].Oneway + "円　定期6か月:" + data7.ResultSet.Course[i].Price[j2 + 5].Oneway + "円<br><br>";
					} else {
						data3 += "<br>";
					}
				}

				document.getElementById("result2").innerHTML = data3;

			}
			else {

				if (data7.ResultSet.Course.Teiki) {
					data3 += "<h2><div class='colorc'><font color='#ffffff'>▷ " + data7.ResultSet.Course.Teiki.DisplayRoute + "</font></div></h2>";
				} else {
					data3 += "<h2><div class='colorc'><font color='#ffffff'>▷ " + resu + "--" + resu2 + "</font></div></h2>";
				}

				for (let j = 0; j < data7.ResultSet.Course.Price.length; j++) {
					if (data7.ResultSet.Course.Price[j].kind == "FareSummary") {
						j2 = j;
						break;
					}
				}

				data3 += "　片道運賃:" + data7.ResultSet.Course.Price[j2].Oneway + "円　往復運賃:" + data7.ResultSet.Course.Price[j2].Round + "円<br>";
				if (data7.ResultSet.Course.Price[j2 + 5]) {
					data3 += "　定期1か月:" + data7.ResultSet.Course.Price[j2 + 1].Oneway + "円　定期3か月:" + data7.ResultSet.Course.Price[j2 + 3].Oneway + "円　定期6か月:" + data7.ResultSet.Course.Price[j2 + 5].Oneway + "円<br><br>";
				}

			}

			//data3 += "<h1>以上、"+count+"件ヒットしました。</h1><br>";
			//document.getElementById("station2").value = data2.ResultSet.Point[0].Station.Name;
			//document.getElementById("stid").value = data2.ResultSet.Point[0].Station.code;
			//document.getElementById("pref").value = data2.ResultSet.Point[0].Prefecture.Name;
			//document.getElementById("data3").onsubmit();
			//location.href = "/result";

		} else {
			data3 += "<h1>経路情報はありませんでした。</h1><br>";
		}

	}

	/*
	
	document.getElementById("result2").innerHTML = data3;
	   */

}

callApi();