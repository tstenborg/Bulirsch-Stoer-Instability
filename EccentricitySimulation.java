
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

public class EccentricitySimulation {

  public static void main(String[] args) throws Exception {

	Trace[] traces = new Trace[4];
	String plot_title = new java.lang.String("Eccentricity Simulation Over 200,000 Years");
	String x_axis = new String("Years");
	String y_axis = new String("Eccentricity");

	// Read data from the target CSV.
    Table ORSA = Table.read().csv("ORSA_Output.csv");
    NumericColumn<?> x = ORSA.nCol("time");
    NumericColumn<?> yJupCalc = ORSA.nCol("e Jup calc");
    NumericColumn<?> yJupRK = ORSA.nCol("e Jup Runge-Kutta"); 
    NumericColumn<?> yJupBS = ORSA.nCol("e Jup Bulirsch-Stoer");    
    NumericColumn<?> ySatCalc = ORSA.nCol("e Sat calc");
    NumericColumn<?> ySatRK = ORSA.nCol("e Sat Runge-Kutta");
    NumericColumn<?> ySatBS = ORSA.nCol("e Sat Bulirsch-Stoer");

    // Configure plot traces.
    ScatterTrace traceJupExp = ScatterTrace.builder(x, yJupCalc)
    		.mode(ScatterTrace.Mode.LINE)
    		.name("Jupiter, expected")
    		.line(Line.builder().color("#000000").dash(Line.Dash.DASH_DOT).build()) // Black.
    		.build();
    //
    ScatterTrace traceJupSimRK = ScatterTrace.builder(x, yJupRK)
    		.mode(ScatterTrace.Mode.LINE)
    		.name("Jupiter, simulated")
    		.line(Line.builder().color("#6a7a89").build()) // Dark grey.
    		.build();
    //
    ScatterTrace traceJupSimBS = ScatterTrace.builder(x, yJupBS)
    		.mode(ScatterTrace.Mode.LINE)
    		.name("Jupiter, simulated")
    		.line(Line.builder().color("#6a7a89").build()) // Dark grey.
    		.build();
    //
    ScatterTrace traceSatExp = ScatterTrace.builder(x, ySatCalc)
    		.mode(ScatterTrace.Mode.LINE)
    		.name("Saturn, expected")
    		.line(Line.builder().color("#000000").dash(Line.Dash.DOT).build()) // Black.
    		.build();
    //
    ScatterTrace traceSatSimRK = ScatterTrace.builder(x, ySatRK)
    		.mode(ScatterTrace.Mode.LINE)
    		.name("Saturn, simulated")
    		.line(Line.builder().color("#51b6d5").build()) // Blue.
    		.build();
    //
    ScatterTrace traceSatSimBS = ScatterTrace.builder(x, ySatBS)
    		.mode(ScatterTrace.Mode.LINE)
    		.name("Saturn, simulated")
    		.line(Line.builder().color("#51b6d5").build()) // Blue.
    		.build();

    // Plot results of Runge-Kutta simulation.
    Layout layoutRK = Layout.builder()
    		.paperBgColor("#ebedef")     // Light grey background.
    		.plotBgColor("#ebedef")      // Light grey background.
    		.title(plot_title + " (Runge-Kutta)")
    		.xAxis(Axis.builder().title(x_axis).build())
    		.yAxis(Axis.builder().title(y_axis).build())
    		.build();
    traces[0] = traceJupExp;    
    traces[1] = traceJupSimRK;
    traces[2] = traceSatExp;
    traces[3] = traceSatSimRK;    
    Plot.show(new Figure(layoutRK, traces));

    // Plot results of Bulirsch-Stoer simulation.
    Layout layoutBS = Layout.builder()
    		.paperBgColor("#ebedef")     // Light grey background.
    		.plotBgColor("#ebedef")      // Light grey background.
    		.title(plot_title + " (Bulirsch-Stoer)")
    		.xAxis(Axis.builder().title(x_axis).build())
    		.yAxis(Axis.builder().title(y_axis).build())
    		.build();
    traces[0] = traceJupExp;
    traces[1] = traceJupSimBS;
    traces[2] = traceSatExp;    
    traces[3] = traceSatSimBS;    
    Plot.show(new Figure(layoutBS, traces));    
  }
}
