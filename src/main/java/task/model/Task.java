package task.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long taskId;

	@Column(nullable = false)
	@Builder.Default
	private String taskText = "New task";

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	private LocalDate actionDate;

	@Column(nullable = false)
	@Builder.Default
	private boolean isComplete = false;

	@Column(nullable = false)
	@Builder.Default
	private boolean isDelete = false;


	@Override
	public String toString() {
		return "Task{" +
				"id=" + taskId +
				", title='" + taskText + '\'' +
				", completed=" + isComplete +
				'}';
	}
}