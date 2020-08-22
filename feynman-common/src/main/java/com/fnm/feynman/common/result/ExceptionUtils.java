package com.fnm.feynman.common.result;

import com.fnm.feynman.common.em.FastEnum;
import com.fnm.feynman.common.exception.CommonException;

/**
 *
 * @author 刘彦军
 *
 */
public class ExceptionUtils {

	public static CommonException create(FastResponse<?> fastResponse) {

		return new CommonException(fastResponse.getCode(), fastResponse.getMessage(), fastResponse.getSubMessage());
	}

	public static CommonException create(FastEnum fastEnum) {

		return new CommonException(fastEnum.getCode(), fastEnum.getMessage());
	}

	public static CommonException create(FastEnum fastEnum, Throwable throwable) {

		return new CommonException(fastEnum.getCode(), fastEnum.getMessage(), throwable);
	}

	public static CommonException create(FastEnum fastEnum, String subMessage) {

		return new CommonException(fastEnum.getCode(), fastEnum.getMessage(), subMessage);
	}

	public static CommonException create(FastEnum fastEnum, String subMessage, Throwable throwable) {

		return new CommonException(fastEnum.getCode(), fastEnum.getMessage(), subMessage, throwable);
	}
}
