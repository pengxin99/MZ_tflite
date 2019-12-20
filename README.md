# MZ_tflite

	1. 数据集 
		a. Input size： 300*300  -->  data/train_img/   data/test_img/
		b. 使用 LabelImg 进行候选框的框定， 生成 .xml 文件  -->   data/train.xml   data/test.xml
		c. Xml to csv
		d. Csv to tfrecord  -->   data/train.record   data/test.record
		e. Label 文件 label.pbtxt
	2. 训练
		a. 安装正确版本的TensorFlow 1.12.1， 避免过高版本对CUDA要求比较高
		b. Git clone model
		c. Cd model/research  设置python路径，防止找不到 object detection 的包，
		export PYTHONPATH=$PYTHONPATH:`pwd`:`pwd`/slim
		d. Model zoo 里面下载对应的预训练模型，这里使用 mobilenetV2+ssd
		（https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/detection_model_zoo.md）
		e. objec_detection/samples/config/ 找到对应config文件，并进行修改
		主要修改：输入输出数据路径，预训练模型路径，输出 checkpoint log 路径，训练配置信息
		f. Object detection 下面： 
			Python legacy/train.py --logtostderr --train_dir=/path/to/checkout/ --pipline_config_path=/path/to/config
	3. 导出 pb 文件 
		a. Python export_inference_graph.py --input_type image_tensor 
		--pipeline_config_path /path/to/config
		--trained_checkpoint_prefix /path/to/checkpoint filename
		--output_directory /path/to/output
		
	4. 远程 tensorboard
		a. ssh -L 16006:127.0.0.1:6006 pengxiny@mlt-gpu200.sh.intel.com
		b. 远程在log目录下 
		tensorboard --logdir=training
		c. 本地浏览器   127.0.0.1:6006
	5. Tflite
https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/running_on_mobile_tensorflowlite.md
