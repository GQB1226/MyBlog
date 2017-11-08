package cn.gqbit.myblog.utils;

/**
 *  操作结果反馈
 */
public class ResultInfo {
    // 操作结果状态
    private String resultStatu;

    // 结果信息
    private String info;

    private Object object;

    public ResultInfo(String resultStatu, String info) {
        this(resultStatu, info, null);
    }

    public ResultInfo(String resultStatu, String info, Object object) {
        this.resultStatu = resultStatu;
        this.info = info;
        this.object = object;
    }

    public String getResultStatu() {
        return resultStatu;
    }

    public void setResultStatu(String resultStatu) {
        this.resultStatu = resultStatu;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
