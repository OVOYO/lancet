package me.ele.lancet.weaver.internal;

import me.ele.lancet.weaver.ClassData;
import me.ele.lancet.weaver.Weaver;
import me.ele.lancet.weaver.internal.asm.ClassTransform;
import me.ele.lancet.weaver.internal.entity.TransformInfo;
import me.ele.lancet.weaver.internal.graph.Graph;
import me.ele.lancet.weaver.internal.log.Log;


/**
 * Created by gengwanpeng on 17/3/21.
 */
public class AsmWeaver implements Weaver {

    /**
     * Create a AsmWeaver instance. In a compilation process, the AsmWeaver instance will only be created once.
     *
     * @param transformInfo the transformInfo for this compilation process.
     * @param graph
     * @return
     */
    public static Weaver newInstance(TransformInfo transformInfo, Graph graph) {
        return new AsmWeaver(transformInfo, graph);
    }

    private final TransformInfo transformInfo;
    private final Graph graph;

    private AsmWeaver(TransformInfo transformInfo, Graph graph) {
        Log.d(transformInfo.toString());
        this.graph = graph;
        this.transformInfo = transformInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassData[] weave(byte[] input, String relativePath) {
        return ClassTransform.weave(transformInfo, graph, input, relativePath);
    }

}
