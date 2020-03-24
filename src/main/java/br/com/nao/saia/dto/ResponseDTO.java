package br.com.nao.saia.dto;

public class ResponseDTO<D> {

    private boolean status;
    private String message;
    private D data;
    
    public ResponseDTO() {
	}
    
    public static <D> ResponseDTO<D> success(D data) {
    	return buildSuccess(data, "Operação realizada com sucesso");
    }
    
	public static <D> ResponseDTO<D> buildSuccess(D data, String message) {
    	ResponseDTO<D> response = new ResponseDTO<>();
    	response.setData(data);
    	response.setStatus(true);
    	response.setMessage(message);
		return response;
	}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}
    
}
