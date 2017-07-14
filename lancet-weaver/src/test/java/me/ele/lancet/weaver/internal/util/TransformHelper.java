package me.ele.lancet.weaver.internal.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.ele.lancet.weaver.ClassData;
import me.ele.lancet.weaver.internal.asm.ClassTransform;
import me.ele.lancet.weaver.internal.entity.TransformInfo;
import okio.Buffer;
import okio.Okio;

/**
 * Created by Jude on 2017/4/28.
 */

public class TransformHelper {

    public static void startTransform(TransformInfo transformInfo) throws IOException {
        List<File> files = ClassFileUtil.getClassPackageFiles("com.sample.playground");
        for (File file : files) {
            ClassData[] datas = ClassTransform.weave(transformInfo, graph, Okio.buffer(Okio.source(file)).readByteArray(), relativePath);
            for (ClassData data : datas) {
                Buffer buffer = new Buffer();
                buffer.write(data.getClassBytes());
                buffer.readAll(Okio.sink(ClassFileUtil.clearFile(ClassFileUtil.getProductFile(data.getClassName()))));
            }
        }
    }
}
