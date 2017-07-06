package Common;
public class Label
{
	private String labelId;
	private double score;
	private String belong;
	private String content;
	private String type;
	public int compareTo(Label o) { //排序函数
	if(this.score<o.score) return 1;
	else return 0;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double sore) {
		this.score = sore;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
