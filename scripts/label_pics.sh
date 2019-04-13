python -m scripts.label_image \
    --graph=tf_files/retrained_graph.pb  \
    --image=tf_files/flower_photos/roses/2414954629_3708a1a04d.jpg 
#:<<EOF
#source activate tensorflow
#python3 -V
path=$1
files=$(ls $path)
for filename in $files

do
    echo $filename
    
    python -m scripts.label_image \
    --graph=tf_files/retrained_graph.pb  \
    --image=$path/$filename \
    --except_type="md"

    #echo $filename >> filename.txt
done
#EOF

