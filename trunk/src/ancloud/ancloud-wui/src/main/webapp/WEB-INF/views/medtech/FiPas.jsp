<%@include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="content-header">
		<h3>Patient PAS trigger history</h3>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-style">
		<style>
			.axis--x path {
				display: none;
			}
			
			.line {
				fill: none;
				stroke: steelblue;
				stroke-width: 1.5px;
			}
		</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="page-script">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/4.4.0/d3.min.js"></script>
		<script type="text/javascript">
			var svg = d3.select("svg"),
				margin = {top: 20, right: 20, bottom: 30, left: 50},
				width = +svg.attr("width") - margin.left - margin.right,
				height = +svg.attr("height") - margin.top - margin.bottom,
				g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			var parseTime = d3.timeParse("%d-%b-%y");
			
			var x = d3.scaleTime()
				.rangeRound([0, width]);
			
			var y = d3.scaleLinear()
				.rangeRound([height, 0]);
			
			var line = d3.line()
				.x(function(d) { return x(d.date); })
				.y(function(d) { return y(d.close); });
			
			var data = [{date:"24-Apr-06",close:93.24},
							{date:"25-Apr-06",close:95.35},
							{date:"26-Apr-06",close:98.84},
							{date:"27-Apr-06",close:99.92},
							{date:"30-Apr-06",close:99.80},
							{date:"1-May-06",close:99.47},
							{date:"2-May-06",close:100.39},
							{date:"3-May-06",close:100.40},
							{date:"4-May-06",close:100.81},
							{date:"7-May-06",close:103.92},
							{date:"8-May-06",close:105.06},
							{date:"9-May-06",close:106.88},
							{date:"10-May-06",close:107.34},
							{date:"11-May-06",close:108.74},
							{date:"14-May-06",close:109.36},
							{date:"15-May-06",close:107.52},
							{date:"16-May-06",close:107.34},
							{date:"17-May-06",close:109.44},
							{date:"18-May-06",close:110.02},
							{date:"21-May-06",close:111.98},
							{date:"22-May-06",close:113.54},
							{date:"23-May-06",close:112.89},
							{date:"24-May-06",close:110.69},
							{date:"25-May-06",close:113.62},
							{date:"29-May-06",close:114.35},
							{date:"30-May-06",close:118.77},
							{date:"31-May-06",close:121.19},
							{date:"1-Jun-06",close:118.40},
							{date:"4-Jun-06",close:121.33},
							{date:"5-Jun-06",close:122.67},
							{date:"6-Jun-06",close:123.64},
							{date:"7-Jun-06",close:124.07},
							{date:"8-Jun-06",close:124.49},
							{date:"11-Jun-06",close:120.19},
							{date:"12-Jun-06",close:120.38},
							{date:"13-Jun-06",close:117.50},
							{date:"14-Jun-06",close:118.75},
							{date:"15-Jun-06",close:120.50},
							{date:"18-Jun-06",close:125.09},
							{date:"19-Jun-06",close:123.66},
							{date:"20-Jun-06",close:121.55},
							{date:"21-Jun-06",close:123.90},
							{date:"22-Jun-06",close:123.00},
							{date:"25-Jun-06",close:122.34},
							{date:"26-Jun-06",close:119.65},
							{date:"27-Jun-06",close:121.89},
							{date:"28-Jun-06",close:120.56},
							{date:"29-Jun-06",close:122.04},
							{date:"2-Jul-06",close:121.26},
							{date:"3-Jul-06",close:127.17},
							{date:"5-Jul-06",close:132.75},
							{date:"6-Jul-06",close:132.30},
							{date:"9-Jul-06",close:130.33},
							{date:"10-Jul-06",close:132.35},
							{date:"11-Jul-06",close:132.39},
							{date:"12-Jul-06",close:134.07},
							{date:"13-Jul-06",close:137.73},
							{date:"16-Jul-06",close:138.10},
							{date:"17-Jul-06",close:138.91},
							{date:"18-Jul-06",close:138.12},
							{date:"19-Jul-06",close:140.00},
							{date:"20-Jul-06",close:143.75},
							{date:"23-Jul-06",close:143.70},
							{date:"24-Jul-06",close:134.89},
							{date:"25-Jul-06",close:137.26},
							{date:"26-Jul-06",close:146.00},
							{date:"27-Jul-06",close:143.85},
							{date:"30-Jul-06",close:141.43},
							{date:"31-Jul-06",close:131.76},
							{date:"1-Aug-06",close:135.00},
							{date:"2-Aug-06",close:136.49},
							{date:"3-Aug-06",close:131.85},
							{date:"6-Aug-06",close:135.25},
							{date:"7-Aug-06",close:135.03},
							{date:"8-Aug-06",close:134.01},
							{date:"9-Aug-06",close:126.39},
							{date:"10-Aug-06",close:125.00},
							{date:"13-Aug-06",close:127.79},
							{date:"14-Aug-06",close:124.03},
							{date:"15-Aug-06",close:119.90},
							{date:"16-Aug-06",close:117.05},
							{date:"17-Aug-06",close:122.06},
							{date:"20-Aug-06",close:122.22},
							{date:"21-Aug-06",close:127.57},
							{date:"22-Aug-06",close:132.51},
							{date:"23-Aug-06",close:131.07},
							{date:"24-Aug-06",close:135.30},
							{date:"27-Aug-06",close:132.25},
							{date:"28-Aug-06",close:126.82},
							{date:"29-Aug-06",close:134.08},
							{date:"30-Aug-06",close:136.25},
							{date:"31-Aug-06",close:138.48},
							{date:"4-Sep-06",close:144.16},
							{date:"5-Sep-06",close:136.76},
							{date:"6-Sep-06",close:135.01},
							{date:"7-Sep-06",close:131.77},
							{date:"10-Sep-06",close:136.71},
							{date:"11-Sep-06",close:135.49},
							{date:"12-Sep-06",close:136.85},
							{date:"13-Sep-06",close:137.20},
							{date:"14-Sep-06",close:138.81},
							{date:"17-Sep-06",close:138.41},
							{date:"18-Sep-06",close:140.92},
							{date:"19-Sep-06",close:140.77},
							{date:"20-Sep-06",close:140.31},
							{date:"21-Sep-06",close:144.15},
							{date:"24-Sep-06",close:148.28},
							{date:"25-Sep-06",close:153.18},
							{date:"26-Sep-06",close:152.77},
							{date:"27-Sep-06",close:154.50},
							{date:"28-Sep-06",close:153.47},
							{date:"1-Oct-06",close:156.34},
							{date:"2-Oct-06",close:158.45},
							{date:"3-Oct-06",close:157.92},
							{date:"4-Oct-06",close:156.24},
							{date:"5-Oct-06",close:161.45},
							{date:"8-Oct-06",close:167.91},
							{date:"9-Oct-06",close:167.86},
							{date:"10-Oct-06",close:166.79},
							{date:"11-Oct-06",close:162.23},
							{date:"12-Oct-06",close:167.25},
							{date:"15-Oct-06",close:166.98},
							{date:"16-Oct-06",close:169.58},
							{date:"17-Oct-06",close:172.75},
							{date:"18-Oct-06",close:173.50},
							{date:"19-Oct-06",close:170.42},
							{date:"22-Oct-06",close:174.36},
							{date:"23-Oct-06",close:186.16},
							{date:"24-Oct-06",close:185.93},
							{date:"25-Oct-06",close:182.78},
							{date:"26-Oct-06",close:184.70},
							{date:"29-Oct-06",close:185.09},
							{date:"30-Oct-06",close:187.00},
							{date:"31-Oct-06",close:189.95},
							{date:"1-Nov-06",close:187.44},
							{date:"2-Nov-06",close:187.87},
							{date:"5-Nov-06",close:186.18},
							{date:"6-Nov-06",close:191.79},
							{date:"7-Nov-06",close:186.30},
							{date:"8-Nov-06",close:175.47},
							{date:"9-Nov-06",close:165.37},
							{date:"12-Nov-06",close:153.76},
							{date:"13-Nov-06",close:169.96},
							{date:"14-Nov-06",close:166.11},
							{date:"15-Nov-06",close:164.30}];
				data.forEach(function(item) {  
					item.date = parseTime(item.date);
					item.close = +item.close;
				;
				x.domain(d3.extent(data, function(d) { 
					return d.date;
					}));
				y.domain(d3.extent(data, function(d) { return d.close; }));
			
				g.append("g")
					.attr("class", "axis axis--x")
					.attr("transform", "translate(0," + height + ")")
					.call(d3.axisBottom(x));
			
				g.append("g")
					.attr("class", "axis axis--y")
					.call(d3.axisLeft(y))
				.append("text")
					.attr("fill", "#000")
					.attr("transform", "rotate(-90)")
					.attr("y", 6)
					.attr("dy", "0.71em")
					.style("text-anchor", "end")
					.text("Price ($)");
			
				g.append("path")
					.datum(data)
					.attr("class", "line")
					.attr("d", line);
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="content-body">
		<div class="row">
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header with-border"></div>
					<div class="box-content">
						<svg width="960" height="500"></svg>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


