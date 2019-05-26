
function setElecDayData(data,type) {
  // 近7天耗电量
  var energyWeek = {
    value: [],
    xAxisData: []
  }
  if(!data.length) return
  for(var i=0;i<data.length;i++){
    var elec = data[i];
    energyWeek.value.push(elec[type])
    energyWeek.xAxisData.push(getDay(elec.recordTime))
  }
  setLineOption('elec',energyWeek)
}

function setWaterDayData(data, type) {
  // 近7天耗水量
  var waterWeek = {
    value: [],
    xAxisData: []
  }
  if(!data.length) return
  for(var i=0;i<data.length;i++){
    var water = data[i];
    waterWeek.value.push(water[type])
    waterWeek.xAxisData.push(getDay(water.recordTime))
  }
  setLineOption('water',waterWeek)
}
function setElecHourData(data, type) {
  // TODO 同比耗电（暂时只有当日数据只显示日消耗量）
  var energyDay = {
    value: [],
    prevValue: [],
    xAxisData: []
  }
  if(!data.length) return
  for(var i=0;i<data.length;i++){
    var elec = data[i];
    energyDay.value.push(elec[type])
    // energyDay.prevValue.push(elec.prevTotal)
    energyDay.xAxisData.push(elec.strRecordTime)
  }
  setBarOption('elec',energyDay)
}
function setWaterHourData(data, type) {
  // TODO 同比水（暂时只有当日数据只显示日消耗量）
  var waterDay = {
    value: [],
    prevValue: [],
    xAxisData: []
  }
  if(!data.length) return
  for(var i=0;i<data.length;i++){
    var water = data[i];
    waterDay.value.push(water[type])
    // energyDay.prevValue.push(water.prevTotal)
    waterDay.xAxisData.push(water.strRecordTime)
  }
  setBarOption('water',waterDay)
}

if(document.getElementById('chartBarOfElec')){
  var chartBarOfElec = echarts.init(document.getElementById('chartBarOfElec'));
  var chartBarOfWater = echarts.init(document.getElementById('chartBarOfWater'));
  // var chartBarOfGas = echarts.init(document.getElementById('chartBarOfGas'));
  var chartLineOfElec = echarts.init(document.getElementById('chartLineOfElec'));
  var chartLineOfWater = echarts.init(document.getElementById('chartLineOfWater'));
  // var chartLineOfGas = echarts.init(document.getElementById('chartLineOfGas'));

  function setBarOption(type,data) {
    var xAxisData = data.xAxisData;
    var todayData = data.value
    var yesterdayData = data.prevValue
    var typeMap = {
      water: {
        name: '耗水量',
        unit: '单位：t'
      },
      elec: {
        name: '耗电量',
        unit: '单位：kW·h'
      },
      gas: {
        name: '燃气',
        unit: '单位：m³'
      },
    }
    var barOption = {
      color: ['#3398DB'],
      tooltip : {
        trigger: 'axis',
        axisPointer : {
          type : 'shadow'
        },
        textStyle: {
          fontSize: 10
        }
      },
      grid: {
        top: '30px',
        left: '12px',
        right: '1px',
        bottom: '5px',
        containLabel: true
      },
      xAxis : [
        {
          type : 'category',
          data : xAxisData,
          axisLine: {
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1,
            }
          },
          axisLabel: {
            interval: 0,
            textStyle:{
              fontSize:7,
              color: '#9fc0cc'
            }
          },
          splitLine: {
            show: true,
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1,
              type: 'solid'
            }
          },
          axisTick: {
            alignWithLabel: true,
            length: 0
          }
        }
      ],
      yAxis : [
        {
          type: 'value',
          name: typeMap[type].unit,
          nameTextStyle: {
            fontSize: 10,
            color: '#9fc0cc',
          },
          axisLine: {
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1
            }
          },
          axisLabel: {
            textStyle:{
              fontSize:8,
              color: '#9fc0cc'
            }
          },
          splitLine: {
            show: true,
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1,
              type: 'solid'
            }
          },
          axisTick: {
            alignWithLabel: true,
            length: 0
          }
        }
      ],
      series : [
        {
          name:'今日'+typeMap[type].name,
          type:'bar',
          barWidth: '30%',
          itemStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1, [{
                  offset: 0,
                  color: '#25ecc0'
                },
                  {
                    offset: 1,
                    color: '#47b1e5'
                  }
                ]
              ),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          data:todayData
        },
        /*{
          name:'昨日'+typeMap[type].name,
          type:'bar',
          barWidth: '30%',
          itemStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1, [{
                  offset: 0,
                  color: '#0c66fb'
                },
                  {
                    offset: 1,
                    color: '#07cee4'
                  }
                ]
              ),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          data: yesterdayData
        }*/
      ]
    };

    if(type == 'water'){
      chartBarOfWater.setOption(barOption);
    }else if(type == 'elec'){
      chartBarOfElec.setOption(barOption);
    }/*else if(type == 'gas'){
      chartBarOfGas.setOption(barOption);
    }*/
  }

  function setLineOption(type,data) {
    var xAxisData = data.xAxisData;
    var data = data.value
    var typeMap = {
      water: {
        name: '耗水量',
        unit: '单位：t'
      },
      elec: {
        name: '耗电量',
        unit: '单位：kW·h'
      },
      gas: {
        name: '燃气',
        unit: '单位：m³'
      },
    }
    var lineOption = {
      color: ['#3398DB'],
      tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
          type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        textStyle: {
          fontSize: 10
        }
      },
      grid: {
        top: '30px',
        left: '5px',
        right: '10px',
        bottom: '5px',
        containLabel: true
      },
      xAxis : [
        {
          type : 'category',
          data : xAxisData,
          axisLine: {
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1
            }
          },
          axisLabel: {
            interval: 0,
            textStyle:{
              fontSize:8,
              color: '#9fc0cc'
            }
          },
          splitLine: {
            show: true,
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1,
              type: 'solid'
            }
          },
          axisTick: {
            alignWithLabel: true,
            length: 0
          }
        }
      ],
      yAxis : [
        {
          type: 'value',
          name: typeMap[type].unit,
          nameTextStyle: {
            fontSize: 10,
            color: '#9fc0cc',
          },
          axisLine: {
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1,
            }
          },
          axisLabel: {
            textStyle:{
              fontSize:8,
              color: '#9fc0cc'
            }
          },
          splitLine: {
            show: true,
            lineStyle:{
              color: 'rgba(17,89,191,.51)',
              width: 1,
              type: 'solid'
            }
          },
          axisTick: {
            alignWithLabel: true,
            length: 0
          }
        }
      ],
      series : [
        {
          name:typeMap[type].name,
          type:'line',
          symbol:"circle",
          symbolSize: '4',
          itemStyle: {
            normal: {
              color: "#26fac9",
              // borderColor: "#26fac9"
            }
          },
          data: data
        }
      ],
      itemStyle: {
        normal: {
          color: ['#26fac9'],
          shadowColor: 'rgba(0, 0, 0, 0.1)',
          shadowBlur: 10
        }
      },
      lineStyle: {
        color: '#26fac9',
        width: 1,
        type: 'solid'
      }
    };
    if(type == 'water'){
      chartLineOfWater.setOption(lineOption);
    }else if(type == 'elec'){
      chartLineOfElec.setOption(lineOption);
    }/*else if(type == 'gas'){
      chartLineOfGas.setOption(lineOption);
    }*/
  }

  function chartResizeEvents(){
    chartBarOfElec.resize();
    chartBarOfWater.resize();
    // chartBarOfGas.resize();
    chartLineOfWater.resize();
    chartLineOfElec.resize();
    // chartLineOfGas.resize();
  }

  window.addEventListener("resize",function(){
    chartResizeEvents();
  });
}

// 水电tabs
function changeTabs() {
  $(".ov-analysis .ov-tab").click(function () {
    var type = $(this).attr('data-type');
    $(this).addClass('active').siblings().removeClass('active');
    $(".ov-analysis .ov-tab-content").removeClass('active');
    $(".ov-analysis .ov-tab-content[data-type='"+type+"']").addClass('active');
    chartResizeEvents();
  });
}

// 报警
function createAlarmList(data) {
  $("#alarmList").empty();
  if(!data.length) return
  for (var i=0;i<data.length;i++){
    var alm = data[i]
    if(alm.sourceState != 'Normal'){
      alm.sourceState = '异常'
    }else{
      alm.sourceState = '正常'
    }
    alm.timestamp = formatTime(alm.timestamp);
    alm.timestamp = alm.timestamp.slice(5,alm.timestamp.length-3);
    var html = '<li class="ov-list-item warning">' +
      '              <span>'+(i+1)+'</span>' +
      '              <span>'+alm.source+'</span>' +
      '              <span>'+alm.alarmClass+'</span>' +
      '              <span>'+alm.timestamp+'</span>' +
      // '              <span>'+alm.sourceState+'</span>' +
      '            </li>';
    $("#alarmList").append(html)
  }
}

//温度
function setGaugeTemp(data, type) {
  var temperatureChart=echarts.init(document.getElementById("gaugeTemperature"));

  var angle = [220, -40];
  var temperature=data,
    curTemp = ((temperature+5)/50).toFixed(3);
  var level = '舒适';
  if(temperature <= 5){
    level = '过冷'
  }else if(temperature > 5 && temperature <= 27){
    level = '舒适'
  }else if(temperature > 27)(
    level = '过热'
  )

  var leftOfTitle = '38%';
  if(type && type == 'overview'){
    leftOfTitle = '36%';
  }

  var option = {
    title: {
      show: true,
      text: '平均温度',
      left: leftOfTitle,
      top: '30%',
      textStyle: {
        fontSize: 10,
        color: '#42a1d4',
        align: 'center'
      }
    },
    backgroundColor: 'transparent',
    tooltip: {
      show: false,
    },
    series: [{
      name: '刻度',
      type: "gauge",
      min: -5,
      max: 45,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [1, "#c1c3c5"]
          ],
          width: 10
        }
      },
      axisTick: {
        lineStyle: {
          color: "#fff",
          width: 1
        },
        length: 0,
        splitNumber: 1
      },
      axisLabel: {
        distance: -25,
        textStyle: {
          fontSize: 8,
          color: '#9fc0cc'
        }
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      itemStyle: {
        normal: {
          color: "#818488",
          shadowColor: 'rgba(0, 0, 0, 0.5)',
          shadowBlur: 5
        }
      },
      detail: {
        formatter: level,
        offsetCenter: [0, "60%"],
        textStyle: {
          fontSize: 14,
          color: "#26fac8"
        }
      },
      title: {
        show: false
      },
      pointer: {
        show: false
      }
    }, {
      name: "内环",
      type: "gauge",
      min: -5,
      max: 45,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [curTemp, '#26fcca'],
            [1, "#115997"]
          ],
          width: 10,
          shadowColor: 'rgba(0, 0, 0, 0.4)',
          shadowBlur: 5
        }
      },
      axisTick: {
        show: false
      },
      axisLabel: {
        show: false
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      pointer: {
        show: false
      },
      detail: {
        fontSize: 20,
        offsetCenter: [0, '6%'],
        formatter: function (value) {
          return value;
        },
        textStyle: {
          color: '#26fac8'
        }
      },
      data:[
        {value:temperature}
      ]
    }]
  }
  temperatureChart.setOption(option);

  window.addEventListener("resize",function(){
    temperatureChart.resize();
  });
}

//湿度
function setGaugeHumidity(data, type) {
  var humidityChart=echarts.init(document.getElementById("gaugeHumidity"));

  var angle = [220, -40];
  var humidity=data,
    curTemp = (humidity/100).toFixed(3);
  var level = '舒适';
  if(humidity <= 30){
    level = '干燥'
  }else if(humidity > 30 && humidity <= 60){
    level = '舒适'
  }else if(humidity > 60)(
    level = '潮湿'
  )

  var leftOfTitle = '38%';
  if(type && type == 'overview'){
    leftOfTitle = '36%';
  }

  var option = {
    title: {
      show: true,
      text: '平均湿度',
      left: leftOfTitle,
      top: '30%',
      textStyle: {
        fontSize: 10,
        color: '#42a1d4',
        align: 'center'
      }
    },
    backgroundColor: 'transparent',
    tooltip: {
      show: false,
    },
    series: [{
      name: '刻度',
      type: "gauge",
      min: 0,
      max: 100,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [1, "#c1c3c5"]
          ],
          width: 5
        }
      },
      axisTick: {
        lineStyle: {
          color: "#fff",
          width: 1
        },
        length: 0,
        splitNumber: 1
      },
      axisLabel: {
        distance: -25,
        textStyle: {
          fontSize: 8,
          color: '#9fc0cc'
        }
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      itemStyle: {
        normal: {
          color: "#818488",
          shadowColor: 'rgba(0, 0, 0, 0.5)',
          shadowBlur: 5
        }
      },
      detail: {
        formatter: level,
        offsetCenter: [0, "60%"],
        textStyle: {
          fontSize: 14,
          color: "#f2471c"
        }
      },
      title: {
        show: false
      },
      pointer: {
        show: false
      }
    }, {
      name: "内环",
      type: "gauge",
      min: 0,
      max: 100,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [curTemp, '#f2471c'],
            [1, "#115997"]
          ],
          width: 10,
          shadowColor: 'rgba(0, 0, 0, 0.4)',
          shadowBlur: 5
        }
      },
      axisTick: {
        show: false
      },
      axisLabel: {
        show: false
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      pointer: {
        show: false
      },
      detail: {
        fontSize: 20,
        offsetCenter: [0, '6%'],
        formatter: function (value) {
          return value;
        }
      },
      data:[
        {value:humidity}
      ]
    }]
  }
  humidityChart.setOption(option);

  window.addEventListener("resize",function(){
    humidityChart.resize();
  });
}

//PM2.5
function setGaugePM25(data, type) {
  var PM25Chart=echarts.init(document.getElementById("gaugePM25"));

  var angle = [220, -40];
  var value=data,
    valueRate = (value / 250).toFixed(3);
  var level = '优';
  if(value <= 35){
    level = '优';
  }else if(value > 35 && value <= 75){
    level = '良'
  }else if(value > 75 && value <= 115){
    level = '轻度污染';
  }else if(value > 115 && value <= 150){
    level = '中度污染';
  }else if(value > 150 && value <= 250){
    level = '重度污染';
  }else if(value > 250){
    level = '严重污染';
  }

  var leftOfTitle = '40%';
  if(type && type == 'overview'){
    leftOfTitle = '38%';
  }

  var option = {
    title: {
      show: true,
      text: 'PM2.5',
      left: leftOfTitle,
      top: '30%',
      textStyle: {
        fontSize: 10,
        color: '#42a1d4',
        align: 'center'
      }
    },
    backgroundColor: 'transparent',
    tooltip: {
      show: false,
    },
    series: [{
      name: '刻度',
      type: "gauge",
      min: 0,
      max: 250,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [1, "#c1c3c5"]
          ],
          width: 10
        }
      },
      axisTick: {
        lineStyle: {
          color: "#fff",
          width: 1
        },
        length: 0,
        splitNumber: 1
      },
      axisLabel: {
        distance: -27,
        textStyle: {
          fontSize: 8,
          color: '#9fc0cc'
        }
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      itemStyle: {
        normal: {
          color: "#818488",
          shadowColor: 'rgba(0, 0, 0, 0.5)',
          shadowBlur: 5
        }
      },
      detail: {
        formatter: level,
        offsetCenter: [0, "60%"],
        textStyle: {
          fontSize: 14,
          color: "#26fac8"
        }
      },
      title: {
        show: false
      },
      pointer: {
        show: false
      }
    }, {
      name: "内环",
      type: "gauge",
      min: 0,
      max: 250,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [valueRate, '#26fcca'],
            [1, "#115997"]
          ],
          width: 10,
          shadowColor: 'rgba(0, 0, 0, 0.4)',
          shadowBlur: 5
        }
      },
      axisTick: {
        show: false
      },
      axisLabel: {
        show: false
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      pointer: {
        show: false
      },
      detail: {
        fontSize: 16,
        offsetCenter: [0, '6%'],
        formatter: function (value) {
          return value;
        }
      },
      data:[
        {value:value}
      ]
    }]
  }
  PM25Chart.setOption(option);

  window.addEventListener("resize",function(){
    PM25Chart.resize();
  });
}

//co2浓度
function setGaugeCO2(data, type) {
  var CO2Chart=echarts.init(document.getElementById("gaugeCO2"));

  var angle = [220, -40];
  var humidity=data,
    curTemp = (humidity/10000).toFixed(3);
  var level = '低';
  if(humidity <= 450){
    level = '优'
  }else if(humidity > 450 && humidity <= 1000){
    level = '良'
  }else if(humidity > 1000 && humidity <= 2000){
    level = '轻度污染'
  }else if(humidity > 2000 && humidity <= 5000){
    level = '中度污染'
  }else if(humidity > 5000){
    level = '严重污染'
  }

  var leftOfTitle = '38%';
  if(type && type == 'overview'){
    leftOfTitle = '37%';
  }

  var option = {
    title: {
      show: true,
      text: 'CO2浓度',
      left: leftOfTitle,
      top: '30%',
      textStyle: {
        fontSize: 10,
        color: '#42a1d4',
        align: 'center'
      }
    },
    backgroundColor: 'transparent',
    tooltip: {
      show: false,
    },
    series: [{
      name: '刻度',
      type: "gauge",
      min: 0,
      max: 10000,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [1, "#c1c3c5"]
          ],
          width: 5
        }
      },
      axisTick: {
        lineStyle: {
          color: "#fff",
          width: 1
        },
        length: 0,
        splitNumber: 1
      },
      axisLabel: {
        distance: -30,
        textStyle: {
          fontSize: 8,
          color: '#9fc0cc'
        }
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      itemStyle: {
        normal: {
          color: "#818488",
          shadowColor: 'rgba(0, 0, 0, 0.5)',
          shadowBlur: 5
        }
      },
      detail: {
        formatter: level,
        offsetCenter: [0, "60%"],
        textStyle: {
          fontSize: 14,
          color: "#26fac8"
        }
      },
      title: {
        show: false
      },
      pointer: {
        show: false
      }
    }, {
      name: "内环",
      type: "gauge",
      min: 0,
      max: 10000,
      radius: '60%',
      startAngle: angle[0],
      endAngle: angle[1],
      splitNumber: 10,
      axisLine: {
        lineStyle: {
          color: [
            [curTemp, '#26fac8'],
            [1, "#115997"]
          ],
          width: 10,
          shadowColor: 'rgba(0, 0, 0, 0.4)',
          shadowBlur: 5
        }
      },
      axisTick: {
        show: false
      },
      axisLabel: {
        show: false
      },
      splitLine: {
        show: true,
        length: 10,
        lineStyle: {
          color: '#fff',
          width: 1
        }
      },
      pointer: {
        show: false
      },
      detail: {
        fontSize: 20,
        offsetCenter: [0, '6%'],
        formatter: function (value) {
          return value;
        }
      },
      data:[
        {value:humidity}
      ]
    }]
  }
  CO2Chart.setOption(option);

  window.addEventListener("resize",function(){
    CO2Chart.resize();
  });
}

function formatTime(timestamp) {
  var time = new Date(timestamp);
  var y = time.getFullYear();
  var m = time.getMonth()+1;
  var d = time.getDate();
  var h = time.getHours();
  var mm = time.getMinutes();
  var s = time.getSeconds();
  return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function add0(m){return m<10?'0'+m:m }

function getDay(time) {
  return time.split(' ')[0].slice(5)
}

function getHour(time) {
  return time.split(' ')[1].slice(0,5)
}

function toThousands(num) {
  return (num.toFixed(0) || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
}
