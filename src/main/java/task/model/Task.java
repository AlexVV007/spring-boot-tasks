package task.model;

import lombok.*;


import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	private Long taskId;

	@NonNull
	@Builder.Default
	private String taskText = "New task";

	private LocalDateTime actionDate;

	@NonNull
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@NonNull
	@Builder.Default
	private Boolean isComplete = false;

	@NonNull
	private Long userId;

	@NonNull
	@Builder.Default
	private Boolean isDelete = false;


	@Override
	public String toString() {
		String s = "Task{" +
				"id=" + taskId +
				", title='" + taskText;
		return s ;
	}
}