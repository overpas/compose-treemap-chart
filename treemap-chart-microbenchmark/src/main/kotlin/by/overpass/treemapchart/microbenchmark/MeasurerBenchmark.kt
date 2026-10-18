package by.overpass.treemapchart.microbenchmark

import by.overpass.treemapchart.core.measure.TreemapNode
import by.overpass.treemapchart.core.measure.sliceanddice.SliceAndDiceMeasurer
import by.overpass.treemapchart.core.measure.squarified.SquarifiedMeasurer
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlin.random.Random

private const val WIDTH = 1080
private const val HEIGHT = 1920
private const val SEED = 42
private const val MIN_VALUE = 1.0
private const val MAX_VALUE = 100.0

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.MICROSECONDS)
class MeasurerBenchmark {

    private lateinit var values: List<Double>

    @Param("10", "100", "1000", "10000")
    lateinit var nodeCount: String

    @Setup
    fun setUp() {
        val random = Random(SEED)
        values = List(nodeCount.toInt()) { random.nextDouble(MIN_VALUE, MAX_VALUE) }
            .sortedDescending()
    }

    @Benchmark
    fun squarified(): List<TreemapNode> =
        SquarifiedMeasurer().measureNodes(values, WIDTH, HEIGHT)

    @Benchmark
    fun sliceAndDice(): List<TreemapNode> =
        SliceAndDiceMeasurer.measureNodes(values, WIDTH, HEIGHT)
}
